package object Chapter6 {
  //定义当前包共享的属性和方法
  val commonValue = "yichen"
  def commonMethod()={
    println(s"I'm $commonValue")
  }
}
