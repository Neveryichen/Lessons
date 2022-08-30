package SparkCore.RDD.Partition

import org.apache.spark.{HashPartitioner, Partitioner, SparkConf, SparkContext}

object Spark01_RDD_Partition {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("Operator") //【*】代表根据本机自身CPU核数建立多线程，不加则默认单线程
    val sc = new SparkContext(sparkConf)

    val rdd = sc.makeRDD(List(
      ("nba", "xxxxxxxxx"),
      ("cba", "xxxxxxxxx"),
      ("wnba", "xxxxxxxxx"),
      ("nba", "xxxxxxxxx"),
    ))

    val partRDD = rdd.partitionBy(new MyPartitioner)

    partRDD.saveAsTextFile("output")

    sc.stop()
  }

  /*
    自定义分区器
    1.继承Partitioner
    2.重写方法
   */
  class MyPartitioner extends Partitioner {
    //分区的数量
    override def numPartitions: Int = 3

    //根据数据的key值返回数据的分区索引（从0开始）
    override def getPartition(key: Any): Int = {
      key match {
        case "nba" => 0
        case "wnba" => 1
        case "cba" => 2
        //模式匹配最好设置通配符来包含所有情况
        case _ => 2
      }

      //      if(key == "nba"){
      //        0
      //      }else if (key == "wnba"){
      //        1
      //      }else if (key == "cba"){
      //        2
      //      }else{
      //        2
      //      }

    }
  }
}
