package Chapter6

import scala.beans.BeanProperty

object Test03_Class {
  def main(args: Array[String]): Unit = {
    //创建一个对象
    val student = new Student()
//    student.name //不能访问私有属性
    println(student.age)
    println(student.sex)
    student.sex = "female"
    println(student.sex)
  }
}


//定义一个类
class Student {
  //定义对应的属性和方法
  val name: String = "alice"
  @BeanProperty
  var age: Int = _
  var sex:String = _

}