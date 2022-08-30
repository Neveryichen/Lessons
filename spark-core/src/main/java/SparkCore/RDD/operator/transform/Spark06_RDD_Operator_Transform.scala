package SparkCore.RDD.operator.transform

import org.apache.spark.{SparkConf, SparkContext}

object Spark06_RDD_Operator_Transform {
  def main(args: Array[String]): Unit = {

    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("Operator") //【*】代表根据本机自身CPU核数建立多线程，不加则默认单线程
    val sc = new SparkContext(sparkConf)

    //TODO 算子 - groupBy
    val rdd = sc.makeRDD(List(1, 2, 3, 4), 2)

    //groupBy将数据源中的每一个数据进行分组判断，根据返回的分组key进行分组
    //相同key值的数据会放置在一个组中
    def groupFunction(num: Int) = {
      num % 2
    }

    val groupRDD = rdd.groupBy(groupFunction)

    groupRDD.collect().foreach(println)
    sc.stop()
  }
}
