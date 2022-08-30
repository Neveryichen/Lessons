package SparkCore.RDD.operator.transform

import org.apache.spark.{SparkConf, SparkContext}

import java.text.SimpleDateFormat

object Spark07_RDD_Operator_Transform {
  def main(args: Array[String]): Unit = {

    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("Operator") //【*】代表根据本机自身CPU核数建立多线程，不加则默认单线程
    val sc = new SparkContext(sparkConf)

    //TODO 算子 - filter
    val rdd = sc.makeRDD(List(1,2,3,4))

    val filterRDD = rdd.filter(num => num % 2 != 0)//进行信息过滤，此为筛选出奇数数据，不同分区内的数据过滤后可能数量差距会很大，从而产生数据倾斜
    filterRDD.collect().foreach(println)





    sc.stop()
  }
}
