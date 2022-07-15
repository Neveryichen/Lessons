package com.yichen.bigdata.spark.RDD.operator.action

import org.apache.spark.{SparkConf, SparkContext}

object Spark02_RDD_Operator_Action {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("Operator") //【*】代表根据本机自身CPU核数建立多线程，不加则默认单线程
    val sc = new SparkContext(sparkConf)

    val rdd = sc.makeRDD(List(1,2,3,4))

    //TODO 行动算子
    //行动算子：触发作业（Job）执行的方法
    //底层代码调用的是环境对象的runJob方法
    //底层代码中会创建activejob，并提交执行

//    //reduce
//    val reduceRDD = rdd.reduce(_ + _)
//    println(reduceRDD)

    //collect:将不同分区的数据按照分区顺序采集到Driver端内存中，形成数组
    val collectRDD = rdd.collect()
    println(collectRDD.mkString(","))


    //count:统计数据源中的数据个数
    val countRDD = rdd.count()
    println(countRDD)

    //first：获取数据源中的第一个数据
    val firstRDD = rdd.first()
    println(firstRDD)

    //take:获取N个数据
    val takeRDD = rdd.take(3)
    println(takeRDD.mkString(","))

    //takeOrdered:数据排序后，取N个数据
    val rdd1 = sc.makeRDD(List(4,2,3,1))
    val takeOrderedRDD = rdd.takeOrdered(3)
    println(takeOrderedRDD.mkString(","))


    sc.stop()
  }
}
