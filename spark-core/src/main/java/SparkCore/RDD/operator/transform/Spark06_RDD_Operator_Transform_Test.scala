package SparkCore.RDD.operator.transform

import org.apache.spark.{SparkConf, SparkContext}

import java.text.SimpleDateFormat

object Spark06_RDD_Operator_Transform_Test {
  def main(args: Array[String]): Unit = {

    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("Operator") //【*】代表根据本机自身CPU核数建立多线程，不加则默认单线程
    val sc = new SparkContext(sparkConf)

    //TODO 算子 - groupBy
    val rdd = sc.textFile("datas/apache.log")

    val timeRDD = rdd.map(
      line => {
        val datas = line.split(" ")
        val time = datas(3)
        val sdf = new SimpleDateFormat("dd/MM/yyyy:HH:mm:ss") //一个更改时间格式的方法，可以自定义年月日等分割的符号
        val date = sdf.parse(time)  //parse函数可以将字符串格式转换为时间格式，但如果格式不匹配会报错
        val sdf2 = new SimpleDateFormat("HH")
        val hour = sdf2.format(date)
        (hour, 1)
      }
    ).groupBy(_._1)

    timeRDD.map{
      case(hour,iter)=>{
        (hour,iter.size)
      }
    }.collect().foreach(println)


    sc.stop()
  }
}
