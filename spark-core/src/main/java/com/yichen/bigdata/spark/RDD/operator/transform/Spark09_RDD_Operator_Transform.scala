package com.yichen.bigdata.spark.RDD.operator.transform

import org.apache.spark.{SparkConf, SparkContext}

object Spark09_RDD_Operator_Transform {
  def main(args: Array[String]): Unit = {

    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("Operator") //【*】代表根据本机自身CPU核数建立多线程，不加则默认单线程
    val sc = new SparkContext(sparkConf)

    //TODO 算子 - distinct
    //可以将数据去重
    val rdd = sc.makeRDD(List(1,2,3,4,1,2,3,4))

    val rdd1 = rdd.distinct()

    rdd1.collect().foreach(println)


    sc.stop()
  }
}
