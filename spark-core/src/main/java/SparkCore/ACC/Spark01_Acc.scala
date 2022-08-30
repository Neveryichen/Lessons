package SparkCore.ACC

import org.apache.spark.{SparkConf, SparkContext}

object Spark01_Acc {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("Operator") //【*】代表根据本机自身CPU核数建立多线程，不加则默认单线程
    val sc = new SparkContext(sparkConf)

    val rdd = sc.makeRDD(List(1, 2, 3, 4))

    //获取系统累加器
    //Spark默认提供了简单数据聚合的累加器
    val sumAcc = sc.longAccumulator("sum")

    //sc.doubleAccumulator()
    //sc.collectionAccumulator()


    rdd.foreach(
      num=>{
        //使用累加器
        sumAcc.add(num)
      }
    )

    //获取累加器的值
    println(sumAcc.value)

    sc.stop()
  }
}
