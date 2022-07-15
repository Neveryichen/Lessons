package com.yichen.bigdata.spark.RDD.operator.transform

import org.apache.spark.{SparkConf, SparkContext}

object Spark12_RDD_Operator_Transform {
  def main(args: Array[String]): Unit = {

    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("Operator") //【*】代表根据本机自身CPU核数建立多线程，不加则默认单线程
    val sc = new SparkContext(sparkConf)

    //TODO 算子 - sortBy
    //可以将数据去重
    val rdd = sc.makeRDD(List(5,6,4,3,1,2),2)

    val newRDD = rdd.sortBy(num=>num)


    newRDD.saveAsTextFile("output")

    sc.stop()
  }
}
