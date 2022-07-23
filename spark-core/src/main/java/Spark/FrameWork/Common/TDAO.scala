package Spark.FrameWork.Common

import Spark.FrameWork.Util.EnvUtil

trait TDAO {
  def readFile(path:String)={
    EnvUtil.take().textFile(path)
  }
}
