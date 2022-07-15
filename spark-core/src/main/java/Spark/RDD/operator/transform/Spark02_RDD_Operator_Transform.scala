package Spark.RDD.operator.transform

import org.apache.spark.{SparkConf, SparkContext}

object Spark02_RDD_Operator_Transform {
  def main(args: Array[String]): Unit = {

    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("Operator")//【*】代表根据本机自身CPU核数建立多线程，不加则默认单线程
    val sc = new SparkContext(sparkConf )

    //TODO 算子 - mapPartitions
    val rdd = sc.makeRDD(List(1, 2, 3, 4), 2)

    /*
    mapPartitions：可以以分区为单位进行数据转换操作
    但是会将整个分区的数据加载到内存进行引用
    所以处理完的数据是不会释放掉的，此时存在对象的引用
    在内存较小，数据量较大的情况下容易出现内存溢出
     */
    val mpRDD = rdd.mapPartitions(
      iter => {
        println(">>>>>>>>>>>>")
        iter.map(_ * 2)
      }
    )
    mpRDD.collect().foreach(println)

    sc.stop()
  }
}
