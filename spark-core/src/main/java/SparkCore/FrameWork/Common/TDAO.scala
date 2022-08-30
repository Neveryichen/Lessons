package SparkCore.FrameWork.Common

import SparkCore.FrameWork.Util.EnvUtil

trait TDAO {
  def readFile(path:String)={
    EnvUtil.take().textFile(path)
  }
}
