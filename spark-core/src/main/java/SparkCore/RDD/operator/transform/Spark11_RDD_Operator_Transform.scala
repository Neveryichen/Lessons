package SparkCore.RDD.operator.transform

import org.apache.spark.{SparkConf, SparkContext}

object Spark11_RDD_Operator_Transform {
  def main(args: Array[String]): Unit = {

    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("Operator") //【*】代表根据本机自身CPU核数建立多线程，不加则默认单线程
    val sc = new SparkContext(sparkConf)

    //TODO 算子 - repartition
    //可以将数据去重
    val rdd = sc.makeRDD(List(1,2,3,4,5,6),2)

    //coalesce算子可以扩大分区，但是如果不进行shuffle操作，就没有意义，不起作用。
    //如果想要实现扩大分区的效果，则必须进行shuffle操作
    //可以用coalesce进行缩减分区操作，而扩大分区有更简化的操作
    val newRdd = rdd.coalesce(3,true)

    //底层代码调用coalesce，默认shuffle为true
    val newRDD = rdd.repartition(3)
    newRdd.saveAsTextFile("output")

    sc.stop()
  }
}
