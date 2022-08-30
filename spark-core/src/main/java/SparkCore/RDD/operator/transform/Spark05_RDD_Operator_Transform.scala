package SparkCore.RDD.operator.transform

import org.apache.spark.{SparkConf, SparkContext}

object Spark05_RDD_Operator_Transform {
  def main(args: Array[String]): Unit = {

    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("Operator") //【*】代表根据本机自身CPU核数建立多线程，不加则默认单线程
    val sc = new SparkContext(sparkConf)

    //TODO 算子 - glom
    val rdd = sc.makeRDD(List(1,2,3,4),2)
    //使用map或flatMap可以将List=>Int，使用glom则可以将Int类型转换为Array类型
    val glomRDD = rdd.glom()

    glomRDD.collect().foreach(data=> println(data.mkString(",")))
    sc.stop()
  }
}
