package SparkCore.RDD.operator.transform

import org.apache.spark.{SparkConf, SparkContext}

object Spark02_RDD_Operator_Transform_Test {
  def main(args: Array[String]): Unit = {

    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("Operator")//【*】代表根据本机自身CPU核数建立多线程，不加则默认单线程
    val sc = new SparkContext(sparkConf )

    //TODO 算子 - mapPartitions
    val rdd = sc.makeRDD(List(1, 2, 3, 4), 2)

    //取出每个分区的最大值，即【2】与【4】
    val mpRDD = rdd.mapPartitions(
      iter => {
        List(iter.max).iterator
      }
    )
    mpRDD.collect().foreach(println)

    sc.stop()
  }
}
