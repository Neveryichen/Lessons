package com.yichen.bigdata.spark.RDD.operator.transform

import org.apache.spark.{SparkConf, SparkContext}

object Spark12_RDD_Operator_Transform2 {
  def main(args: Array[String]): Unit = {

    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("Operator") //【*】代表根据本机自身CPU核数建立多线程，不加则默认单线程
    val sc = new SparkContext(sparkConf)

    //TODO 算子 - sortBy
    //可以将数据去重
    val rdd = sc.makeRDD(List(("1",1),("11",2),("2",3)),2)

    //sortBy方法可以根据指定的规则对数据源中的数据进行排序，默认为升序，第二个参数写入false可以进行倒序排序
    //sortBy默认情况下，不会改变分区，但是中间存在shuffle操作
    val newRDD = rdd.sortBy(t=>t._1.toInt,ascending = false)

    newRDD.collect().foreach(println)

    sc.stop()
  }
}
