package SparkCore.RDD.serial

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark01_RDD_Serial {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("Operator") //【*】代表根据本机自身CPU核数建立多线程，不加则默认单线程
    val sc = new SparkContext(sparkConf)

    val rdd = sc.makeRDD(Array("hello world", "hello spark", "hive", "yichen"))

    val search = new Search("h")

    search.getMatch1(rdd).collect().foreach(println)

    sc.stop()
  }

  //查询对象
  //类的构造参数其实是类的属性
  class Search(query: String) extends Serializable {

    def isMatch(s: String): Boolean = {
      s.contains(query)
    }

    // 函数序列化案例
    def getMatch1(rdd: RDD[String]): RDD[String] = {
      rdd.filter(isMatch)
    }


    // 属性序列化案例
    def getMatch2(rdd: RDD[String]): RDD[String] = {
      rdd.filter(x => x.contains(query))
    }

  }
}