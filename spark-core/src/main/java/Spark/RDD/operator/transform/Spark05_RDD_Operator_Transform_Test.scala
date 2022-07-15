package Spark.RDD.operator.transform

import org.apache.spark.{SparkConf, SparkContext}

object Spark05_RDD_Operator_Transform_Test {
  def main(args: Array[String]): Unit = {

    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("Operator") //【*】代表根据本机自身CPU核数建立多线程，不加则默认单线程
    val sc = new SparkContext(sparkConf)

    //TODO 算子 - glom
    val rdd = sc.makeRDD(List(1,2,3,4),2)

    //此案例为将每个分区求出最大值并求和
    val glomRDD = rdd.glom()//此行代码将一个分区内的数据变成了一个集合，方便之后的操作
    val maxRDD = glomRDD.map(
      array => {
        array.max
      }
    )


    println(maxRDD.collect().sum)
    sc.stop()
  }
}
