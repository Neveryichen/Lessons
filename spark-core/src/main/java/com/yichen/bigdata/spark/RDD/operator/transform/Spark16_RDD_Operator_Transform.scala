package com.yichen.bigdata.spark.RDD.operator.transform

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark16_RDD_Operator_Transform {
  def main(args: Array[String]): Unit = {

    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("Operator") //【*】代表根据本机自身CPU核数建立多线程，不加则默认单线程
    val sc = new SparkContext(sparkConf)

    //TODO 算子 - Key-Value类型

    val rdd = sc.makeRDD(List(
      ("a",1),("a",2),("a",3),("b",4)
    ))

    //groupByKey：将数据源中的数据，相同key的数据分在一个组中，形成一个对偶元组
    //            元组中的第一个元素就是key
    //            元组中的第二个元素就是对应的value的集合
    val groupRDD: RDD[(String, Iterable[Int])] = rdd.groupByKey()

    groupRDD.collect().foreach(println)
    sc.stop()
  }
}
