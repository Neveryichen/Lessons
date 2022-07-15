package Chapter4

import scala.io.StdIn

object Test01_IfElse {
  def main(args: Array[String]): Unit = {
    println("请输入您的年龄：")
    val age: Int = StdIn.readInt()

    //1.单分支
    if (age >= 18) {
      println("成年")
    }

    println("========================")

    //2.双分支
    if (age >= 18) {
      println("成年")
    }
    else {
      println("未成年")
    }

    //3.多分支
    if (age <= 6) {
      println("童年")
    }
    else if (age < 18) {
      println("青少年")
    }
    else if (age < 36) {
      println("青年")
    }

    println("==========================")

    //4.分支语句的返回值
    val result: Any = if (age <= 6) {
      println("童年")
      "童年" //此处为返回值，不可以加return，根据定义的方法类型来确定返回值的类型，String即为字符串。
    }
    else if (age < 18) {
      println("青少年")
      "青少年"
    }
    else if (age < 36) {
      println("青年")
      "青年"
    }
    else {
      age
    }
    println("result:" + result)

    //Java中的三元运算a?b:c
    val res = if (age >= 18) "成年" else "未成年" //成年与未成年均为两次判断语句的返回值

    //5.嵌套分支
    if (age >= 18) {
      println("成年")
      if (age >= 35) {
        if (age >= 60) {
          println("老年")
        }
        else {
          println("中年")
        }
      }
      else {
        println("青年")
      }
    }
    else {
      if (age <= 6) {
        println("童年")
      } else {
        println("青少年")
      }
    }
  }
}
