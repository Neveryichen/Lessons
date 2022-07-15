package Chapter6

object Test17_Extends {
  def main(args: Array[String]): Unit = {
    //1.类型的检测和转换
    val student :Student17 = new Student17("alice",18)
    student.study()
    student.sayHi()
    val person:Person17 = new Student17("bob",20)
    person.sayHi()

    //类型的判断
    println("student is student17:" + student.isInstanceOf[Student17])
    println("student is Person17:" + student.isInstanceOf[Person17])
    println("person is Person17:" + person.isInstanceOf[Person17])
    println("person is student17:" + person.isInstanceOf[Student17])

    //类型转化
    if (person.isInstanceOf[Student17]){
      val newStudent = person.asInstanceOf[Student17]
      newStudent.study()
    }

    println(classOf[Student17])

    //2.测试枚举类
    println(WorkDay.MONDAY)


  }
}


class Person17(val name:String,val age:Int) {
  def sayHi(): Unit = {
    println("hi from person:" + name)
  }
}

class Student17(name:String,age:Int) extends Person17(name,age) {
  override def sayHi(): Unit = {
    println("hi from student:" + name)
  }
  def study(): Unit ={
    println("student study")
  }
}

//定义枚举类对象
object WorkDay extends Enumeration{
  val MONDAY = Value(1,"Monday")
  val TUESDAY = Value(2,"Tuesday")
}

//定义应用类对象
object TestApp extends App{
  println("app start")

  type a = String
  val a:a = "abc"
}