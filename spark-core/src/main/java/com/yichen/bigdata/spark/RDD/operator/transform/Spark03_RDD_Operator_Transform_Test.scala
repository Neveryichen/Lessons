package com.yichen.bigdata.spark.RDD.operator.transform

import org.apache.spark.{SparkConf, SparkContext}

object Spark03_RDD_Operator_Transform_Test {
  def main(args: Array[String]): Unit = {

    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("Operator")//【*】代表根据本机自身CPU核数建立多线程，不加则默认单线程
    val sc = new SparkContext(sparkConf )

    //TODO 算子 - mapPartitions
    val rdd = sc.makeRDD(List(1, 2, 3, 4), 2)

    //使用WithIndex的函数可以实现查看数据存储的分区号，并对其进行一系列操作，下面为对特定序列号进行筛选
    val mpiRDD = rdd.mapPartitionsWithIndex(
      (index, iter) => {
        if (index == 1) {
          iter
        } else {
          Nil.iterator  //返回一个空迭代器
        }

      }
    )
    mpiRDD.collect().foreach(println)

    sc.stop()
  }
}
