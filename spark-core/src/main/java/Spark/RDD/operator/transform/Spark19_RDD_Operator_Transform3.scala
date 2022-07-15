package Spark.RDD.operator.transform

import org.apache.spark.{SparkConf, SparkContext}

object Spark19_RDD_Operator_Transform3 {
  def main(args: Array[String]): Unit = {

    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("Operator") //【*】代表根据本机自身CPU核数建立多线程，不加则默认单线程
    val sc = new SparkContext(sparkConf)

    //TODO 算子 - Key-Value类型

    val rdd = sc.makeRDD(List(
      ("a", 1), ("a", 2), ("b", 3), ("b", 4), ("b", 5), ("a", 6)
    ), 2)


    //第一个参数表示：将相同的key的第一个数据进行结构的转换，从而实现操作
    //第二个参数表示：分区内的计算规则
    //第三个参数表示：分区间的计算规则
    val newRDD = rdd.combineByKey(
      v => (v, 1), //第一个参数
      (t: (Int, Int), v) => {
        (t._1 + v, t._2 + 1) //t._1表示rdd列表中某个分区的数据的总value值（0+v1+v2），t._2表示有几个数据（0+1+1）
      }, //第一个函数为分区内计算。第一个函数意为将每个value分类并统一，从Int变成一个tuple
      (t1: (Int, Int), t2: (Int, Int)) => {
        (t1._1 + t2._1, t1._2 + t2._2) //第二个函数为分区间计算，负责统一不同的key值并且运算。第二个函数中，t._1和t._2表示两个处理好的数据，t._1表示value值的加和，t._2表示value个数的统计。
      }
    )

    val resultRDD = newRDD.mapValues { //将newRDD中求出的元组value进行计算，用总数除以个数以求得平均值
      case (num, cnt) => {
        num / cnt
      }
    }
    resultRDD.collect().foreach(println)

    sc.stop()
  }
}
