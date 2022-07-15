package Spark.RDD.operator.transform

import org.apache.spark.{SparkConf, SparkContext}

object Spark10_RDD_Operator_Transform {
  def main(args: Array[String]): Unit = {

    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("Operator") //【*】代表根据本机自身CPU核数建立多线程，不加则默认单线程
    val sc = new SparkContext(sparkConf)

    //TODO 算子 - coalesce
    //可以将数据去重
    val rdd = sc.makeRDD(List(1,2,3,4,5,6),3)

    /*
    coalesce算子默认情况下不会将分区的数据打乱重新组合,这种情况下的缩减分区可能会导致数据不均衡，出现数据倾斜
    如果想要数据均衡，可以进行shuffle操作，即传入第二个参数布尔值，true时会进行shuffle将数据打乱使其均衡。
    经过测试shuffle后数据被随机打乱，但打乱后数据固定，不论执行多少次都会是一样的打乱结果
     */
    val newRdd = rdd.coalesce(2)//true)

    newRdd.saveAsTextFile("output")

    sc.stop()
  }
}
