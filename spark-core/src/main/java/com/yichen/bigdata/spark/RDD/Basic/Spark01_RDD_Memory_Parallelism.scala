package com.yichen.bigdata.spark.RDD.Basic

import org.apache.spark.{SparkConf, SparkContext}

object Spark01_RDD_Memory_Parallelism {
  def main(args: Array[String]): Unit = {
    //TODO 准备环境
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD") //【*】代表根据本机自身CPU核数建立多线程，不加则默认单线程
    sparkConf.set("spark.default.parallelism", "5") //自己配置生成的分区数
    val sc = new SparkContext(sparkConf)

    //TODO 创建RDD
    //RDD的并行度&分区
    //makeRDD可以传递第二个参数，这个参数表示分区的数量
    //第二个参数可以不传递，不传递则makeRDD使用默认值：defaultParallelism（默认并行度）
    //spark在默认情况下，从配置对象中获取配置参数：spark.default.parallelism
    //如果获取不到，那么使用totalCores属性，这个属性取值为当前运行环境的最大可用核数
    val rdd = sc.makeRDD(
      List(1, 2, 3, 4), 2
    )

    //将处理的数据保存成分区文件
    rdd.saveAsTextFile("output")

    //TODO 关闭环境
    sc.stop()

  }
}
