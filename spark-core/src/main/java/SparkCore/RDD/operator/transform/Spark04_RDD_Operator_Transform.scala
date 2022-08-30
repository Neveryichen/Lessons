package SparkCore.RDD.operator.transform

import org.apache.spark.{SparkConf, SparkContext}

object Spark04_RDD_Operator_Transform {
  def main(args: Array[String]): Unit = {

    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("Operator")//【*】代表根据本机自身CPU核数建立多线程，不加则默认单线程
    val sc = new SparkContext(sparkConf )

    //TODO 算子 - flatMap
    val rdd = sc.makeRDD(List(
      List(1,2),List(3,4)
    ))
    //扁平化，可以将list中的数据拆开
    val flatRDD = rdd.flatMap(  //两个List不是同一个List，第一个为上面传入的List，第二个为扁平化后输出的List
      List => {
        List
      }
    )

    flatRDD.collect().foreach(println)

    sc.stop()
  }
}
