package Spark.RDD.Basic

import org.apache.spark.{SparkConf, SparkContext}

object Spark01_RDD_Memory_Parallelism2 {
  def main(args: Array[String]): Unit = {
    //TODO 准备环境
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD") //【*】代表根据本机自身CPU核数建立多线程，不加则默认单线程
    val sc = new SparkContext(sparkConf)

    //TODO 创建RDD
    //【1，2】，【3，4】
    //    val rdd = sc.makeRDD(
    //      List(1, 2, 3, 4), 2
    //    )

    //【1】，【2】，【3，4】
    //    val rdd = sc.makeRDD(
    //      List(1, 2, 3, 4), 3
    //    )

    //【1】，【2，3】，【4，5】
    /*
    切片算法：
    val start = ((i*length)/(numSlices).toInt
    val end =(((i+1)*length)/numSlices).toInt
    i为每个切片的编号，从0开始
    length为list的长度，下面List中为5；numSlices为切片的数量，下面List中为3
    用此算法算出start与end，作为每一个分区的头尾，from start，until end
    说明包含start，不包含end
     */
    val rdd = sc.makeRDD(
      List(1, 2, 3, 4, 5), 3
    )

    //将处理的数据保存成分区文件
    rdd.saveAsTextFile("output")

    //TODO 关闭环境
    sc.stop()

  }
}
