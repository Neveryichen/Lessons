package com.yichen.bigdata.spark.RDD.operator.action

import org.apache.spark.{SparkConf, SparkContext}

object Spark06_RDD_Operator_Action {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("Operator") //【*】代表根据本机自身CPU核数建立多线程，不加则默认单线程
    val sc = new SparkContext(sparkConf)

    //TODO 行动算子

    val rdd = sc.makeRDD(List(1, 2, 3, 4), 2)

    //接在collect后面的foreach是Driver端内存集合的循环遍历方法
    rdd.collect().foreach(println)
    println("========================")
    //直接调用foreach则为Executor端内存数据打印
    rdd.foreach(println)

    //算子：Operator（操作）
    /*
    RDD的方法和Scala集合对象的方法不一样
    集合对象的方法都是同一个节点的内存中完成的
    而RDD的方法可以将逻辑发送到Executor端（分布式节点）执行
    为了区分不同的处理效果，所以将RDD的方法称之为算子
    RDD的方法外部的操作（例如第16行的println）都是在Driver端执行的，而方法内部的逻辑代码（例如18行foreach函数中的println）是在Executor端执行的
     */
    sc.stop()
  }
}
