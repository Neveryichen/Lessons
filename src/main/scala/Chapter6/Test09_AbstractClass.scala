package Chapter6

object Test09_AbstractClass {
  def main(args: Array[String]): Unit = {
    val student = new Student9
    student.eat()
    student.sleep()
  }
}

//定义抽象类
abstract class Person9 {
  //定义非抽象属性
  val name: String = "person"

  //抽象属性
  var age: Int

  //非抽象方法
  def eat() = {
    println("person eat")
  }

  //抽象方法
  def sleep(): Unit
}

//定义具体的实现子类
class Student9 extends Person9 {
  //实现抽象属性和方法
  var age = 18

  override def sleep(): Unit = {
    println("student never sleep")
  }

  //重写非抽象属性和方法
  override val name: String = "Student"

  override def eat(): Unit ={
    super.eat()
    println("Student always eat")
  }
}