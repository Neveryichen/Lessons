package com.yichen.bigdata.spark.RDD.operator.transform

import org.apache.spark.{HashPartitioner, SparkConf, SparkContext}

object Spark14_RDD_Operator_Transform {
  def main(args: Array[String]): Unit = {

    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("Operator") //【*】代表根据本机自身CPU核数建立多线程，不加则默认单线程
    val sc = new SparkContext(sparkConf)

    //TODO 算子 - Key-Value类型

    val rdd1 = sc.makeRDD(List(1, 2, 3, 4))

    val mapRDD = rdd1.map((_,1))

    //RDD=>PairRDDFunctions
    //隐式转换（二次编译）

    //partitionBy根据指定的分区规则对数据进行重分区
    mapRDD.partitionBy(new HashPartitioner(2)).saveAsTextFile("output")

    sc.stop()
  }
}
