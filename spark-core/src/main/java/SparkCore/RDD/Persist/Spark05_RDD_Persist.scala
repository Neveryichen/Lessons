package SparkCore.RDD.Persist

import org.apache.spark.{SparkConf, SparkContext}

object Spark05_RDD_Persist {
  def main(args: Array[String]): Unit = {


    val sparkConf = new SparkConf().setMaster("local").setAppName("Operator") //【*】代表根据本机自身CPU核数建立多线程，不加则默认单线程
    val sc = new SparkContext(sparkConf)

    sc.setCheckpointDir("cp")

    val list = List("Hello Scala", "Hello Spark")

    val rdd = sc.makeRDD(list)

    val flatRDD = rdd.flatMap(_.split(" "))

    val mapRDD = flatRDD.map(word => {
      println("调用了mapRDD")
      (word, 1)
    })
    /*
    cache：将数据临时存储在内存中进行数据重用，不安全
           会在血缘关系中添加新的依赖，一旦出现问题，可以从头读取数据
    persist：将数据临时存储在磁盘文件中进行数据重用，它涉及到磁盘IO，性能较低但是数据安全
             如果作业执行完毕，临时保存的数据文件就会丢失
             会在血缘关系中添加新的依赖，一旦出现问题，可以从头读取数据
    checkpoint：将数据长久的保存在磁盘IO，性能较低，但是数据安全
                为了保证数据安全，所以一般情况下，会独立执行作业
                为了能够提高效率，一般情况下需要和cache联合使用
                执行过程中会切断血缘关系，重新建立新的血缘
                checkpoint等同于改变数据源
     */

    mapRDD.cache()

    mapRDD.checkpoint()

    val reduceRDD = mapRDD.reduceByKey(_ + _)

    reduceRDD.collect().foreach(println)

    println("=====================")

    val groupRDD = mapRDD.groupByKey()
    groupRDD.collect().foreach(println)

  }
}
