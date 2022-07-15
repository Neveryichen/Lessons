package Chapter5

object Test01_Function {
  def main(args: Array[String]): Unit = {
    //定义函数
    def sayHi(name:String):Unit = {
      println("Hi," + name)
    }

    //调用函数
    sayHi("alice")

    //调用对象方法
    Test01_Function.sayHi("BOB")

    //获取方法返回值
    val result = Test01_Function.sayHello("cary")
    println(result)
  }


  //定义对象的方法
  def sayHi(name:String):Unit = {
    println("Hi there," + name)
  }

  def sayHello(name:String):String = {
    println("Hi," + name)
    return "Hello"
  }
}
