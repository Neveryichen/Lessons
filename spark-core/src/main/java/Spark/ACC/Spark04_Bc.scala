package Spark.ACC

import org.apache.spark.util.AccumulatorV2
import org.apache.spark.{SparkConf, SparkContext}

import scala.collection.mutable

object Spark04_Bc {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("Operator") //【*】代表根据本机自身CPU核数建立多线程，不加则默认单线程
    val sc = new SparkContext(sparkConf)

    val rdd1 = sc.makeRDD(List(
      ("a", 1), ("b", 2), ("c", 3)
    ))
    val rdd2 = sc.makeRDD(List(
      ("a", 4), ("b", 5), ("c", 6)
    ))

    val map = mutable.Map(("a", 4), ("b", 5), ("c", 6))

    //封装广播变量
    val bc = sc.broadcast(map)

    //val joinRDD = rdd1.join(rdd2)

    //join会导致数据量的几何增长，并且会影响shuffle的性能，不推荐使用
    //joinRDD.collect().foreach(println)

    rdd1.map {
      case (w, c) => {
        val value = bc.value.getOrElse(w, 0)  //访问广播变量
        (w, (c, value))
      }
    }.collect().foreach(println)

    sc.stop()
  }
}
