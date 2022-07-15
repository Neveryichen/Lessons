package Chapter2

import scala.io.StdIn

object Test05_StdIn {
  def main(args: Array[String]): Unit = {
    //输入信息
    println("请输入姓名：")
    val name = StdIn.readLine()
    println("请输入年龄：")
    val age = StdIn.readInt()

    //控制台打印输出
    println(s"欢迎${age}岁的${name}前来")
  }
}
