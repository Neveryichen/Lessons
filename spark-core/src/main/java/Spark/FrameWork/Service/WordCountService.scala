package Spark.FrameWork.Service

import Spark.FrameWork.Common.TService
import Spark.FrameWork.DAO.WordCountDAO
import org.apache.spark.rdd.RDD

/**
 * 服务层
 */
class WordCountService extends TService {
  private val wordCountDAO = new WordCountDAO()

  //数据分析
  def dataAnalysis() = {

    val lines = wordCountDAO.readFile("datas/11.txt")
    val words: RDD[String] = lines.flatMap(_.split(" "))
    val wordToOne = words.map(word => (word, 1))
    val wordGroup: RDD[(String, Iterable[(String, Int)])] = wordToOne.groupBy(t => t._1)
    val wordToCount = wordGroup.map {
      case (word, list) => {

        list.reduce(
          (t1, t2) => {
            (t1._1, t1._2 + t2._2)
          }
        )
      }
    }
    val array = wordToCount.collect()
    array
  }
}
