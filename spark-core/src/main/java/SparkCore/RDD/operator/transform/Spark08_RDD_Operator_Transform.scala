package SparkCore.RDD.operator.transform

import org.apache.spark.{SparkConf, SparkContext}

object Spark08_RDD_Operator_Transform {
  def main(args: Array[String]): Unit = {

    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("Operator") //【*】代表根据本机自身CPU核数建立多线程，不加则默认单线程
    val sc = new SparkContext(sparkConf)

    //TODO 算子 - sample
    val rdd = sc.makeRDD(List(1,2,3,4,5,6,7,8,9,10))

    //sample算子可以用来判断数据倾斜问题

    /*
    sample算子需要传递三个参数:
    1.第一个参数表示，抽取数据后是否将数据返回。true表示放回，false表示不放回
    2.第二个参数表示数据源中每天数据被抽取的概率
      设定第二个参数的意义：为当种子判定一个数据被抽取的概率后，大于第二个参数的基准值则数据会被表现出来（抽取到），若小于基准值则等于没有被抽取到
      当抽取不放回时，第二个参数为基准值的定义
      当抽取放回时，第二个参数表示数据源的每条数据被抽取的可能次数
    3.第三个参数表示抽取数据时随机算法的种子
      如果不传递第三个参数，那么使用的是当前的系统时间作为种子
     */
    println(rdd.sample(
      false,
      0.4,
      //1
       //种子确定好则抽取的数字就已经确定了
    ).collect().mkString(","))





    sc.stop()
  }
}
