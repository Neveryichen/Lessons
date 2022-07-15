package Spark.RDD.operator.action

import org.apache.spark.{SparkConf, SparkContext}

object Spark03_RDD_Operator_Action {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("Operator") //【*】代表根据本机自身CPU核数建立多线程，不加则默认单线程
    val sc = new SparkContext(sparkConf)

    val rdd = sc.makeRDD(List(1,2,3,4),2)

    //TODO 行动算子
    //行动算子：触发作业（Job）执行的方法
    //底层代码调用的是环境对象的runJob方法
    //底层代码中会创建activejob，并提交执行

    //aggregateByKey只会参与分区内计算，而aggregate会参与分区内与分区间的计算
    val result = rdd.aggregate(10)(_ + _, _ + _)

    //fold:对应foldByKey，指分区内和分区间进行一样的计算，逻辑与aggregate相同，指分区内和分区间都会进行计算
    val fold = rdd.fold(10 )(_+_)

    println(fold)

    sc.stop()
  }
}
