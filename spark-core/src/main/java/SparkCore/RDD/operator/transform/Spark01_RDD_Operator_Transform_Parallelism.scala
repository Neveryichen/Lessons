package SparkCore.RDD.operator.transform

import org.apache.spark.{SparkConf, SparkContext}

object Spark01_RDD_Operator_Transform_Parallelism {
  def main(args: Array[String]): Unit = {

    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("Operator") //【*】代表根据本机自身CPU核数建立多线程，不加则默认单线程
    val sc = new SparkContext(sparkConf)

    //TODO 算子 - map
    val rdd = sc.makeRDD(List(1, 2, 3, 4),2)

    /*1.RDD的计算，一个分区内的数据是一个一个执行逻辑
        只有前面数据的全部逻辑执行完毕后，才会执行下一个数据
        分区内数据的执行是有序的
      2.不同分区内数据的执行是无序的
     */
    val mapRDD = rdd.map(
      num => {
        println(">>>>" + num)
        num
      }
    )

    val mapRDD2 = mapRDD.map( //mapRDD2对mapRDD使用了map函数，对其再次封装
      num => {
        println("####" + num)
        num
      }
    )

    mapRDD2.collect()

    sc.stop()
  }
}
