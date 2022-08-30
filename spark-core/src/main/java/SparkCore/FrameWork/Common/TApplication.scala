package SparkCore.FrameWork.Common

import SparkCore.FrameWork.Controller.WordCountController
import SparkCore.FrameWork.Util.EnvUtil
import org.apache.spark.{SparkConf, SparkContext}

trait TApplication {
  def start(master: String = "local[*]", app: String = "app")(op: => Unit) = {
    val sparConf = new SparkConf().setMaster(master).setAppName(app)
    val sc = new SparkContext(sparConf)
    EnvUtil.put(sc)

    try {
      op
    } catch {
      case ex => println(ex.getMessage)
    }

    //TODO 关闭连接
    sc.stop()
    EnvUtil.clear()
  }
}
