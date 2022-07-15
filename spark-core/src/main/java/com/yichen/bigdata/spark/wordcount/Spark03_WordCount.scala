package com.yichen.bigdata.spark.wordcount

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark03_WordCount {
  def main(args: Array[String]): Unit = {
    //Application
    //Spark框架
    //TODO建立和Spark框架的连接
    //JDBC:Connection
    val sparConf = new SparkConf().setMaster("local[*]").setAppName("WordCount")
    val sc = new SparkContext(sparConf)

    //TODO执行业务操作

    val lines: RDD[String] = sc.textFile("datas")

    val words: RDD[String] = lines.flatMap(_.split(" "))

    val wordToOne = words.map(
      word => (word, 1)
    )

    //Spark框架提供了更多的功能，可以将分组和聚合使用一个方法实现
    //reduceByKey：相同的key的数据，可以对value进行reduce聚合
    val wordToCount = wordToOne.reduceByKey(_ + _)

    val array = wordToCount.collect()
    array.foreach(println)

    //TODO关闭连接
    sc.stop()
  }
}
