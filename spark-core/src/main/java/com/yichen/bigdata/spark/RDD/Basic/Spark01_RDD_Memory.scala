package com.yichen.bigdata.spark.RDD.Basic

import org.apache.spark.{SparkConf, SparkContext}

object Spark01_RDD_Memory {
  def main(args: Array[String]): Unit = {
    //TODO 准备环境
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD") //【*】代表根据本机自身CPU核数建立多线程，不加则默认单线程
    val sc = new SparkContext(sparkConf)

    //TODO 创建RDD
    //从内存中创建RDD，将内存中集合的数据作为处理的数据源
    val seq = Seq[Int](1, 2, 3, 4)

    //parallelize：并行，非并发
    //val rdd = sc.parallelize(seq)

    //makeRDD方法在底层实现时就是调用了parallelize的方法
    val rdd = sc.makeRDD(seq)

    rdd.collect().foreach(println)

    //TODO 关闭环境
    sc.stop()

  }
}
