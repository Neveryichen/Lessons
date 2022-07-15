package Spark.RDD.operator.transform

import org.apache.spark.{SparkConf, SparkContext}

object Spark03_RDD_Operator_Transform_Test2 {
  def main(args: Array[String]): Unit = {

    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("Operator")//【*】代表根据本机自身CPU核数建立多线程，不加则默认单线程
    val sc = new SparkContext(sparkConf )

    //TODO 算子 - mapPartitions
    val rdd = sc.makeRDD(List(1, 2, 3, 4))

    //通过map对数据进行转换，打印对应的目录号
    val mpiRDD = rdd.mapPartitionsWithIndex(
      (index, iter) => {
        iter.map(
          num => {
            (index, num)
          }
        )
      }
    )

    mpiRDD.collect().foreach(println)

    sc.stop()
  }
}
