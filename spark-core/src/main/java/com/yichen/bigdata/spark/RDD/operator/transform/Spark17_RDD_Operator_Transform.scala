package com.yichen.bigdata.spark.RDD.operator.transform

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark17_RDD_Operator_Transform {
  def main(args: Array[String]): Unit = {

    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("Operator") //【*】代表根据本机自身CPU核数建立多线程，不加则默认单线程
    val sc = new SparkContext(sparkConf)

    //TODO 算子 - Key-Value类型

    val rdd = sc.makeRDD(List(
      ("a", 1), ("a", 2), ("a", 3), ("b", 4)
    ), 2)
    //(a,[1,2]),(a,[3,4])
    //(a,2),(a,4)
    //(a,6)

    //aggregateByKey存在函数柯里化，有两个参数列表
    //第一个参数列表：需要传递一个参数，表示为初始值，主要用于遇到第一个key时，和它的value进行分区内计算
    //第二个参数列表：需要传递两个参数，第一个表示分区内计算规则，第二个表示分区间计算规则
    rdd.aggregateByKey(0)(
      (x, y) => math.max(x, y),
      (x, y) => x + y
    ).collect().foreach(println)


    sc.stop()
  }
}
