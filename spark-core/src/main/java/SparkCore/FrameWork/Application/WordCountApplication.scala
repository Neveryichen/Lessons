package SparkCore.FrameWork.Application

import SparkCore.FrameWork.Common.TApplication
import SparkCore.FrameWork.Controller.WordCountController

object WordCountApplication extends App with TApplication{

  //启动应用程序
  start(){
    val controller = new WordCountController()
    controller.execute()
  }

}
