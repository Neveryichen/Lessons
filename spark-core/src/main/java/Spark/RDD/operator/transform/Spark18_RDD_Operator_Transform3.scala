package Spark.RDD.operator.transform

import org.apache.spark.{SparkConf, SparkContext}

object Spark18_RDD_Operator_Transform3 {
  def main(args: Array[String]): Unit = {

    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("Operator") //【*】代表根据本机自身CPU核数建立多线程，不加则默认单线程
    val sc = new SparkContext(sparkConf)

    //TODO 算子 - Key-Value类型

    val rdd = sc.makeRDD(List(
      ("a", 1), ("a", 2), ("b", 3), ("b", 4), ("b", 5), ("a", 6)
    ), 2)

    //aggregateByKey存在函数柯里化，有两个参数列表
    //第一个参数列表：需要传递一个参数，表示为初始值，主要用于遇到第一个key时，和它的value进行分区内计算
    //第二个 参数列表：需要传递两个参数，第一个表示分区内计算规则，第二个表示分区间计算规则
    rdd.aggregateByKey(0)(
      (x, y) => math.max(x, y),
      (x, y) => x + y
    ).collect().foreach(println)
    //aggregateByKey最终的返回数据结果应该和初始值的类型保持一致


    //获取相同key的数据的平均值 => (a,3),(b,4)
    /*
    第一个参数传入(0,0)表示kv对里面的value是(0,0)形式的，则返回的数据为(K,(V1,V2))
     */
    val newRDD = rdd.aggregateByKey((0, 0))(
      (t, v) => {
        (t._1 + v, t._2 + 1)  //t._1表示rdd列表中某个分区的数据的总value值（0+v1+v2），t._2表示有几个数据（0+1+1）
      },                      //第一个函数为分区内计算。第一个函数意为将每个value分类并统一，从Int变成一个tuple
      (t1, t2) => {
        (t1._1 + t2._1, t1._2 + t2._2)  //第二个函数为分区间计算，负责统一不同的key值并且运算。第二个函数中，t._1和t._2表示两个处理好的数据，t._1表示value值的加和，t._2表示value个数的统计。
      }
    )

    val resultRDD = newRDD.mapValues {  //将newRDD中求出的元组value进行计算，用总数除以个数以求得平均值
      case (num, cnt) => {
        num / cnt
      }
    }
    resultRDD.collect().foreach(println)

    sc.stop()
  }
}
