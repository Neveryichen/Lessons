package Chapter6

import javax.print.attribute.standard.RequestingUserName

object Test13_Trait {
  def main(args: Array[String]): Unit = {
    val student = new Student13
    student.sayHello()
    student.study()
    student.dating()
    student.play()
  }
}


//定义父类
class Person13 {
  val name: String = "person"
  var age: Int = 18

  def sayHello() = {
    println("Hello from:" + name)
  }

  def increase(): Unit ={
    println("Person increased")
  }
}


//定义一个特质
trait Young {
  //声明抽象和非抽象属性
  var age: Int
  val name: String = "young"

  //声明抽象和未抽象的方法
  def play() = {
    println("young people is playing")
  }

  def dating()
}


class Student13 extends Person13 with Young {
  //重写冲突的属性
  override val name = "student"

  //实现抽象方法
  def dating() = println(s"Student $name is dating")

  def study() = println(s"student $name is studying")

  //重写父类方法
  override def sayHello(): Unit = {
    super.sayHello()
    println(s"Hello from:student $name")
  }
}