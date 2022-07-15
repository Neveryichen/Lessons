package com.yichen.bigdata.spark.RDD.operator.transform

import org.apache.spark.{SparkConf, SparkContext}

object Spark06_RDD_Operator_Transform2 {
  def main(args: Array[String]): Unit = {

    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("Operator") //【*】代表根据本机自身CPU核数建立多线程，不加则默认单线程
    val sc = new SparkContext(sparkConf)

    //TODO 算子 - groupBy
    val rdd = sc.makeRDD(List("Hello", "Spark", "Scala", "Hadoop"), 2)

    /*
    charAt()方法表示某一个字符串中的第几个字符，从0开始，在groupBy中为按照第一个字符进行同类项合并
     */
    /*
    此分组和分区没有必然的关系
    groupBy会将数据打乱，重新组合，这个操作称为shuffle
     */
    val groupRDD = rdd.groupBy(_.charAt(0)) //表示相同的首字母则会放在一起

    groupRDD.collect().foreach(println)

    sc.stop()
  }
}
