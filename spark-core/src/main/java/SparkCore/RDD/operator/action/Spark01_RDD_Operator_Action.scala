package SparkCore.RDD.operator.action

import org.apache.spark.{SparkConf, SparkContext}

object Spark01_RDD_Operator_Action {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("Operator") //【*】代表根据本机自身CPU核数建立多线程，不加则默认单线程
    val sc = new SparkContext(sparkConf)

    val rdd = sc.makeRDD(List(1,2,3,4))

    //TODO 行动算子
    //行动算子：触发作业（Job）执行的方法
    //底层代码调用的是环境对象的runJob方法
    //底层代码中会创建activejob，并提交执行




    sc.stop()
  }
}
