package SparkCore.RDD.operator.transform

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark22_RDD_Operator_Transform3 {
  def main(args: Array[String]): Unit = {

    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("Operator") //【*】代表根据本机自身CPU核数建立多线程，不加则默认单线程
    val sc = new SparkContext(sparkConf)

    //TODO 算子 - Key-Value类型

    val rdd1 = sc.makeRDD(List(
      ("a", 1), ("b", 2)//, ("c", 3)
    ))
    val rdd2 = sc.makeRDD(List(
      ("a", 4), ("b", 5)  , ("c", 6)
    ))

    //val leftjoinRDD = rdd1.leftOuterJoin(rdd2)
    //(a,(1,Some(4)))
    //(b,(2,Some(5)))
    //(c,(3,None))
    val rightjoinRDD = rdd1.rightOuterJoin(rdd2)
    //(a,(Some(1),4))
    //(b,(Some(2),5))
    //(c,(None,6))

    rightjoinRDD.collect().foreach(println)
    sc.stop()
  }
}
