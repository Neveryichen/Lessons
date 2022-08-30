package SparkCore.RDD.operator.action

import org.apache.spark.{SparkConf, SparkContext}

object Spark07_RDD_Operator_Action {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("Operator") //【*】代表根据本机自身CPU核数建立多线程，不加则默认单线程
    val sc = new SparkContext(sparkConf)

    //TODO 行动算子

    val rdd = sc.makeRDD(List(1, 2, 3, 4))


    val user = new User()

//    SparkException: Task not serializable
//    Caused by: java.io.NotSerializableException: com.yichen.bigdata.spark.RDD.Builder.operator.action.Spark07_RDD_Operator_Action$User
//    Serialization stack
//    报错原因：User没有序列化

    //RDD算子中传递的函数会包含闭包操作，会进行检测功能
    //闭包检测
    rdd.foreach(
      num => {
        println("age = "+ (user.age + num))
      }
    )
    sc.stop()
  }

//  class User extends Serializable {

  //样例类在编译时，会自动混入序列化特质（实现可序列化接口）
case class User(){
    var age = 30
  }
}
