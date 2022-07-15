package Spark.RDD.operator.transform

import org.apache.spark.{SparkConf, SparkContext}

object Spark04_RDD_Operator_Transform2 {
  def main(args: Array[String]): Unit = {

    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("Operator")//【*】代表根据本机自身CPU核数建立多线程，不加则默认单线程
    val sc = new SparkContext(sparkConf )

    //TODO 算子 - flatMap
    val rdd = sc.makeRDD(List(
      "Hello Scala","Hello Spark"
    ))

    //扁平化，可以将list中的数据拆开
    val flatRDD = rdd.flatMap(
      s => {
        s.split(" ")
      }
    )

    flatRDD.collect().foreach(println)

    sc.stop()
  }
}
