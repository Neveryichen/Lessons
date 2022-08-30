package SparkCore.RDD.dependence

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.RDD

object Spark01_RDD_Dep {
  def main(args: Array[String]): Unit = {
    val sparConf = new SparkConf().setMaster("local").setAppName("WordCount")
    val sc = new SparkContext(sparConf)

    val lines: RDD[String] = sc.textFile("datas/11.txt")
    println(lines.toDebugString)
    println("========================")


    val words: RDD[String] = lines.flatMap(_.split(" "))
    println(words.toDebugString)
    println("========================")

    val wordGroup: RDD[(String, Iterable[String])] = words.groupBy(word => word)
    println(wordGroup.toDebugString)
    println("========================")

    val wordToCount = wordGroup.map {
      case (word, list) => {
        (word, list.size)
      }
    }
    println(wordToCount.toDebugString)
    println("========================")

    val array = wordToCount.collect()
    array.foreach(println)

    sc.stop()
  }
}
