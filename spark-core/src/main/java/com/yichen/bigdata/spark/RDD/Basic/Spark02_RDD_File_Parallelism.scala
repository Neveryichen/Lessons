package com.yichen.bigdata.spark.RDD.Basic

import org.apache.spark.{SparkConf, SparkContext}

object Spark02_RDD_File_Parallelism {
  def main(args: Array[String]): Unit = {
    //TODO 准备环境
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD") //【*】代表根据本机自身CPU核数建立多线程，不加则默认单线程
    val sc = new SparkContext(sparkConf)

    //TODO 创建RDD
    //textFile可以将文件作为数据处理的数据源，默认也可以设定分区
    //  minPartitions:最小分区数量
    //  math.min(defaultParallelism,2)
    //val rdd = sc.textFile("datas/1.txt")

    //如果不想使用默认的分区数量，可以传入第二个参数
    //Spark读取文件，底层使用了Hadoop的读取方式
    //文件的真实字节数：除了字符外，如每个回车也消耗两个字节
    /*
    分区数量的计算方式：
    totalSize = 7 三个数字与两个空格：3*1+2*2
    goalSize = 7/2 = 3(byte)
    7/3= 2 ... 1
    Hadoop的分区计算逻辑中，余数/分区数后如果小于10%则会并入上一分区，否则将产生一个新的分区
     */
    val rdd = sc.textFile("datas/1.txt", 2)
    rdd.saveAsTextFile("output")
    //TODO 关闭环境
    sc.stop()

  }
}
