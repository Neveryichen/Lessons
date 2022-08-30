package SparkCore.RDD.Basic

import org.apache.spark.{SparkConf, SparkContext}

object Spark02_RDD_File {
  def main(args: Array[String]): Unit = {
    //TODO 准备环境
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD") //【*】代表根据本机自身CPU核数建立多线程，不加则默认单线程
    val sc = new SparkContext(sparkConf)

    //TODO 创建RDD
    //从文件中创建RDD，将文件中的数据作为处理的数据源

    //path路径默认以当前环境的根路径为基准，可以写绝对路径，也可以写相对路径
    //sc.textFile("D:\\SparkProject\\datas\\1.txt"):此为绝对路径
    //val rdd = sc.textFile("datas/1.txt")//此时以默认根路径为基准

    //path路径可以是文件的具体路径，也可以写目录名称
    //val rdd = sc.textFile("datas")//此时会对此路径下的所有文件进行读取

    //path路径还可以使用通配符 *
    val rdd = sc.textFile("datas/1*.txt")

    //path还可以是分布式存储系统路径：HDFS等
    //val rdd = sc.textFile("hdfs://hadoop102:xxxx")

    rdd.collect().foreach(println)

    //TODO 关闭环境
    sc.stop()

  }
}
