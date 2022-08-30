package SparkCore.RDD.operator.transform

import org.apache.spark.{SparkConf, SparkContext}

object Spark01_RDD_Operator_Transform_Test {
  def main(args: Array[String]): Unit = {

    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("Operator")//【*】代表根据本机自身CPU核数建立多线程，不加则默认单线程
    val sc = new SparkContext(sparkConf )

    //TODO 算子 - map
    //获取日志信息中的特定部分
    val rdd = sc.textFile("datas/apache.log")

    //将长的字符串转化成短的字符串
    val mapRDD = rdd.map(
      line => {
        val datas = line.split(" ") //通过空格分割字符串并选中需要的字符串
        datas(6)
      }
    )
    mapRDD.collect().foreach(println)
    sc.stop()
  }
}
