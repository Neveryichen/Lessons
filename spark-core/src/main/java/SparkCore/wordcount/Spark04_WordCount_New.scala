package SparkCore.wordcount

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

import scala.collection.mutable

object Spark04_WordCount_New {
  def main(args: Array[String]): Unit = {
    //TODO 建立和Spark框架的连接
    //JDBC:Connection
    val sparConf = new SparkConf().setMaster("local[*]").setAppName("WordCount")
    val sc = new SparkContext(sparConf)

    //TODO 执行业务操作
    wordcount9(sc)

    //TODO 关闭连接
    sc.stop()
  }

  //groupBy
  def wordcount1(sc: SparkContext) = {
    val rdd = sc.makeRDD(List("Hello Scala", "Hello Spark"))
    val words = rdd.flatMap(_.split(" "))
    val group = words.groupBy(word => word)
    val wordcount = group.mapValues(iter => iter.size)
  }

  //groupByKey
  def wordcount2(sc: SparkContext) = {
    val rdd = sc.makeRDD(List("Hello Scala", "Hello Spark"))
    val words = rdd.flatMap(_.split(" "))
    val wordOne = words.map((_, 1))
    val group = wordOne.groupByKey()
    val wordcount = group.mapValues(iter => iter.size)
  }

  //reduceByKey
  def wordcount3(sc: SparkContext) = {
    val rdd = sc.makeRDD(List("Hello Scala", "Hello Spark"))
    val words = rdd.flatMap(_.split(" "))
    val wordOne = words.map((_, 1))
    val wordcount = wordOne.reduceByKey(_ + _)
  }

  //aggregateByKey
  def wordcount4(sc: SparkContext) = {
    val rdd = sc.makeRDD(List("Hello Scala", "Hello Spark"))
    val words = rdd.flatMap(_.split(" "))
    val wordOne = words.map((_, 1))
    val wordcount = wordOne.aggregateByKey(0)(_ + _, _ + _)
  }

  //foldByKey
  def wordcount5(sc: SparkContext) = {
    val rdd = sc.makeRDD(List("Hello Scala", "Hello Spark"))
    val words = rdd.flatMap(_.split(" "))
    val wordOne = words.map((_, 1))
    val wordcount = wordOne.foldByKey(0)(_ + _)
  }

  //combineByKey
  def wordcount6(sc: SparkContext) = {
    val rdd = sc.makeRDD(List("Hello Scala", "Hello Spark"))
    val words = rdd.flatMap(_.split(" "))
    val wordOne = words.map((_, 1))
    val wordcount = wordOne.combineByKey(
      v => v,
      (x: Int, y) => x + y,
      (x: Int, y: Int) => x + y
    )
  }

  //countByKey
  def wordcount7(sc: SparkContext) = {
    val rdd = sc.makeRDD(List("Hello Scala", "Hello Spark"))
    val words = rdd.flatMap(_.split(" "))
    val wordOne = words.map((_, 1))
    val wordcount = wordOne.countByKey()
  }

  //countByValue
  def wordcount8(sc: SparkContext) = {
    val rdd = sc.makeRDD(List("Hello Scala", "Hello Spark"))
    val words = rdd.flatMap(_.split(" "))
    val wordcount = words.countByValue()
  }

  //reduce
  def wordcount9(sc: SparkContext) = {
    val rdd = sc.makeRDD(List("Hello Scala", "Hello Spark"))
    val words = rdd.flatMap(_.split(" "))
    val mapWord = words.map(
      word => {
        mutable.Map((word, 1))
      }
    )
    val wordcount = mapWord.reduce(
      (map1, map2) => {
        map2.foreach {
          case (word, count) => {
            val newCount = map1.getOrElse(word, 0) + count
            map1.update(word, newCount)
          }
        }
        map1
      }
    )
    println(wordcount)
  }
}
