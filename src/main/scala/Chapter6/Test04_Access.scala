package Chapter6

object Test04_Access {
  def main(args: Array[String]): Unit = {
    //创建对象
    val p : person = new person()
    val w : worker = new worker()
//  person.idCard //私有属性无法访问
//    person.name //保护对象无法访问
    println(p.age)
    println(p.sex)
    p.printInfo()
    w.printInfo()
  }
}

//定义一个子类
class worker extends person{
  override def printInfo(): Unit = {
    println("worker: ")
    name =  "BOB"
    age = 25
    sex = "male"

    println(s"worker: $name $age $sex")

  }
}
