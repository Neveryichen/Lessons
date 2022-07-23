package Spark.FrameWork.Controller


import Spark.FrameWork.Common.TController
import Spark.FrameWork.Service.WordCountService

/**
 * 控制层
 */
class WordCountController extends TController {
  private val wordCountService = new WordCountService()

  //调度
  def execute()={
    //TODO执行业务操作
    val array = wordCountService.dataAnalysis()
    array.foreach(println)
  }
}
