package Spark.RDD.Persist

import org.apache.spark.{SparkConf, SparkContext}

object Spark03_RDD_Persist {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("Operator") //【*】代表根据本机自身CPU核数建立多线程，不加则默认单线程
    val sc = new SparkContext(sparkConf)

    val list = List("Hello Scala", "Hello Spark")

    val rdd = sc.makeRDD(list)

    val flatRDD = rdd.flatMap(_.split(" "))

    val mapRDD = flatRDD.map(word => {
      println("调用了mapRDD")
      (word, 1)
    }) //尽管reduceRDD和groupRDD调用的是一个mapRDD，但因为RDD不保存数据，所以实际上代码从头跑了两遍，效率较低

    /*
    cache默认持久化的操作，只能将数据保存在内存中，如果想要保存到磁盘文件，需要使用persist函数
    在persist函数中传入参数来决定将数据保存在哪里
    注意：持久化操作必须在行动算子执行时完成
     */
    mapRDD.cache()  //加入了cache函数，将RDD中的数据进行了缓存，则意味着进行了数据持久化
    mapRDD.persist()

    val reduceRDD = mapRDD.reduceByKey(_ + _)

    reduceRDD.collect().foreach(println)

    println("=====================")

    val groupRDD = mapRDD.groupByKey()
    groupRDD.collect().foreach(println)

  }
}
