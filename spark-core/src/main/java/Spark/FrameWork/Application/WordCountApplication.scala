package Spark.FrameWork.Application

import Spark.FrameWork.Common.TApplication
import Spark.FrameWork.Controller.WordCountController

object WordCountApplication extends App with TApplication{

  //启动应用程序
  start(){
    val controller = new WordCountController()
    controller.execute()
  }

}
