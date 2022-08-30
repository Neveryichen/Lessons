package SparkCore.RDD.operator.transform

import org.apache.spark.{SparkConf, SparkContext}

object Spark13_RDD_Operator_Transform {
  def main(args: Array[String]): Unit = {

    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("Operator") //【*】代表根据本机自身CPU核数建立多线程，不加则默认单线程
    val sc = new SparkContext(sparkConf)

    //TODO 算子 - 双Value类型

    /*
    交集并集差集要求数据源数据类型保持一致
    拉链会形成tuple元组，不需要类型保持一致
    但是拉链需要分区数量保持一致，并且要求分区中数据数量需要保持一致Spark13_RDD_Operator_Transform$
     */
    val rdd1 = sc.makeRDD(List(1, 2, 3, 4))
    val rdd2 = sc.makeRDD(List(3, 4, 5, 6))

    //交集
    val rdd3 = rdd1.intersection(rdd2)
    println(rdd3.collect().mkString(","))

    //并集
    val rdd4 = rdd1.union(rdd2)
    println(rdd4.collect().mkString(","))

    //差集
    val rdd5 = rdd1.subtract(rdd2)
    println(rdd5.collect().mkString(","))

    //拉链
    val rdd6 = rdd1.zip(rdd2)
    println(rdd6.collect().mkString(","))


    sc.stop()
  }
}
