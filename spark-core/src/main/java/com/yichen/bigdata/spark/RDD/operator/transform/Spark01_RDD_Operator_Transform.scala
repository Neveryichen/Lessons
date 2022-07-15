package com.yichen.bigdata.spark.RDD.operator.transform

import org.apache.spark.{SparkConf, SparkContext}

object Spark01_RDD_Operator_Transform {
  def main(args: Array[String]): Unit = {

    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("Operator")//【*】代表根据本机自身CPU核数建立多线程，不加则默认单线程
    val sc = new SparkContext(sparkConf )

    //TODO 算子 - map
    val rdd = sc.makeRDD(List(1, 2, 3, 4))

    //转换函数
    def mapFunction(num:Int)={
      num*2
    }

    //val mapRDD = rdd.map(mapFunction)
    //val mapRDD = rdd.map((num:Int)=>{num*2})
    val mapRDD = rdd.map(_*2)

    mapRDD.collect().foreach(println)

    sc.stop()
  }
}
