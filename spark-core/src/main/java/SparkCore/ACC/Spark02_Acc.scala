package SparkCore.ACC

import org.apache.spark.{SparkConf, SparkContext}

object Spark02_Acc {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("Operator") //【*】代表根据本机自身CPU核数建立多线程，不加则默认单线程
    val sc = new SparkContext(sparkConf)

    val rdd = sc.makeRDD(List(1, 2, 3, 4))

    //获取系统累加器
    //Spark默认提供了简单数据聚合的累加器
    val sumAcc = sc.longAccumulator("sum")

    //sc.doubleAccumulator()
    //sc.collectionAccumulator()


    rdd.map(
      num=>{
        //使用累加器
        sumAcc.add(num)
        num
      }
    )

    //获取累加器的值
    //少加：转换算子中调用累加器，如果没有行动算子的话，则不会执行
    //多加：调用了多次行动算子，使得累加器作为一个公共变量也调用了多次
    //一般情况下，累加器会发在行动算子中进行操作

    println(sumAcc.value)

    sc.stop()
  }
}
