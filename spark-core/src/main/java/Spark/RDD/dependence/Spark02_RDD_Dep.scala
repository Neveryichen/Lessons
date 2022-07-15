package Spark.RDD.dependence

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark02_RDD_Dep {
  def main(args: Array[String]): Unit = {
    val sparConf = new SparkConf().setMaster("local").setAppName("WordCount")
    val sc = new SparkContext(sparConf)

    val lines: RDD[String] = sc.textFile("datas/11.txt")
    println(lines.dependencies)
    println("========================")


    val words: RDD[String] = lines.flatMap(_.split(" "))
    println(words.dependencies)
    println("========================")

    val wordGroup: RDD[(String, Iterable[String])] = words.groupBy(word => word)
    println(wordGroup.dependencies)
    println("========================")

    val wordToCount = wordGroup.map {
      case (word, list) => {
        (word, list.size)
      }
    }
    println(wordToCount.dependencies)
    println("========================")

    val array = wordToCount.collect()
    array.foreach(println)

    sc.stop()
  }
}
