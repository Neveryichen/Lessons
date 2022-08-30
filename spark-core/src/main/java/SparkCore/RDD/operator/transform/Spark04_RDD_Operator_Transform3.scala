package SparkCore.RDD.operator.transform

import org.apache.spark.{SparkConf, SparkContext}

object Spark04_RDD_Operator_Transform3 {
  def main(args: Array[String]): Unit = {

    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("Operator") //【*】代表根据本机自身CPU核数建立多线程，不加则默认单线程
    val sc = new SparkContext(sparkConf)

    //TODO 算子 - flatMap
    val rdd = sc.makeRDD(List(List(1, 2), 3, List(4, 5)))


    //数据有可能并不统一，此时需要进行模式匹配，使用match方法(优化后可以省略match声明直接使用）
    val flatRDD = rdd.flatMap {
      case list: List[_] => list
      case dat => List(dat)
    }

    flatRDD.collect().foreach(println)

    sc.stop()
  }
}
