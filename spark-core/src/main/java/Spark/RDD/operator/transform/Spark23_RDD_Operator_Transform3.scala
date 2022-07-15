package Spark.RDD.operator.transform

import org.apache.spark.{SparkConf, SparkContext}

object Spark23_RDD_Operator_Transform3 {
  def main(args: Array[String]): Unit = {

    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("Operator") //【*】代表根据本机自身CPU核数建立多线程，不加则默认单线程
    val sc = new SparkContext(sparkConf)

    //TODO 算子 - Key-Value类型

    val rdd1 = sc.makeRDD(List(
      ("a", 1), ("b", 2) //, ("c", 3)
    ))
    val rdd2 = sc.makeRDD(List(
      ("a", 4), ("b", 5), ("c", 6),("c",7)
    ))

    //cogroup : connect + group （分组链接）
    val cgRDD = rdd1.cogroup(rdd2)
//    (a,(CompactBuffer(1),CompactBuffer(4)))
//    (b,(CompactBuffer(2),CompactBuffer(5)))
//    (c,(CompactBuffer(),CompactBuffer(6, 7)))

    cgRDD.collect().foreach(println)

    sc.stop()
  }
}
