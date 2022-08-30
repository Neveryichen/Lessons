package SparkCore.RDD.IO

import org.apache.spark.{SparkConf, SparkContext}

object Spark02_RDD_IO_Load {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("Operator") //【*】代表根据本机自身CPU核数建立多线程，不加则默认单线程
    val sc = new SparkContext(sparkConf)

    //需要现在Spark01中建立三个output文件来进行读取

    val rdd = sc.textFile("output1")
    println(rdd.collect().mkString(","))

    val rdd2 = sc.objectFile[(String, Int)]("output2")
    println(rdd2.collect().mkString(","))

    val rdd3 = sc.sequenceFile[String, Int]("output3")
    println(rdd3.collect().mkString(","))

    sc.stop()
  }
}
