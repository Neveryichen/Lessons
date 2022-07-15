package com.yichen.bigdata.spark.RDD.operator.transform

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark21_RDD_Operator_Transform3 {
  def main(args: Array[String]): Unit = {

    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("Operator") //【*】代表根据本机自身CPU核数建立多线程，不加则默认单线程
    val sc = new SparkContext(sparkConf)

    //TODO 算子 - Key-Value类型

    val rdd1 = sc.makeRDD(List(
      ("a", 1), ("b", 2), ("c", 3),("a",4)
    ))
    val rdd2 = sc.makeRDD(List(
      ("a", 4), ("b", 5), ("c", 6),("a",7)
    ))

    //join算子：两个不同数据源的数据，相同的key的value会连接在一起，组成元组
    //         如果两个数据源中key没有匹配上，那么数据不会出现在结果中
    //         如果两个数据源中key有多个相同的，则会依次匹配，可能会出现笛卡尔乘积，数据量会几何形式增长
    val joinRDD: RDD[(String, (Int, Int))] = rdd1.join(rdd2)

    joinRDD.collect().foreach(println)
    sc.stop()
  }
}
