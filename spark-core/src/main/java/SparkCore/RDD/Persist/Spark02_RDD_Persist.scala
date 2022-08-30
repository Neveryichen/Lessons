package SparkCore.RDD.Persist

import org.apache.spark.{SparkConf, SparkContext}

object Spark02_RDD_Persist {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("Operator") //【*】代表根据本机自身CPU核数建立多线程，不加则默认单线程
    val sc = new SparkContext(sparkConf)

    val list = List("Hello Scala","Hello Spark")

    val rdd = sc.makeRDD(list)

    val flatRDD = rdd.flatMap(_.split(" "))

    val mapRDD = flatRDD.map(word => {
      println("调用了mapRDD")
      (word, 1)
    }) //尽管reduceRDD和groupRDD调用的是一个mapRDD，但因为RDD不保存数据，所以实际上代码从头跑了两遍，效率较低

    val reduceRDD = mapRDD.reduceByKey(_ + _)

    reduceRDD.collect().foreach(println)

    println("=====================")

    val groupRDD = mapRDD.groupByKey()
    groupRDD.collect().foreach(println)

  }
}
