package Chapter6

object Test06_ConstructorParams {
  def main(args: Array[String]): Unit = {
    val student2 = new Student2

    val student3 = new Student3("bob", 20)
    println(s"name : ${student3.name} age: ${student3.age}")

    val student4 = new Student4("cary", 25)
    student4.printinfo()
  }
}


//定义类
//无参构造器
class Student2 {
  //单独定义属性
  var name: String = _
  var age: Int = _

}

//上面定义等价于
class Student3(var name: String, var age: Int)

//主构造器参数无修饰
class Student4(name: String, age: Int) {
  def printinfo(): Unit = {
    println(s"name:$name age:$age")
  }
}

class Student5(val name:String,val age:Int)

class Student6(var name:String,var age:Int)
{
  var school:String= _
  def this(name:String,age:Int,school:String){
    this(name,age)
    this.school = school
  }
}