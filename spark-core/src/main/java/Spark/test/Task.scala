package Spark.test

class Task extends Serializable{ //方法如果想要传递必须要混入一个特质，即继承Serializable

  val datas = List(1, 2, 3, 4)

  val logic: (Int) => Int = _ * 2

}
