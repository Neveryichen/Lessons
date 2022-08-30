package SparkCore.RDD.operator.transform

import org.apache.spark.{HashPartitioner, SparkConf, SparkContext}

object Spark15_RDD_Operator_Transform {
  def main(args: Array[String]): Unit = {

    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("Operator") //【*】代表根据本机自身CPU核数建立多线程，不加则默认单线程
    val sc = new SparkContext(sparkConf)

    //TODO 算子 - Key-Value类型

    val rdd = sc.makeRDD(List(
      ("a",1),("a",2),("a",3),("b",4)
    ))

    //reduceByKey:相同的key的数据进行value数据的聚合操作
    //scala语言中一般的聚合操作为两两聚合，则spark也是两两聚合
    //【1，2，3】=>【3，3】=>【6】
    //reduceByKey中如果key的数据只有一个，则不会参与运算
    val reduceRDD = rdd.reduceByKey((x,y)=>{
      println(s"x=$x , y=$y")
      x+y
    })

    reduceRDD.collect().foreach(println)
    sc.stop()
  }
}
