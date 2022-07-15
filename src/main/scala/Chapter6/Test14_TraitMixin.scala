package Chapter6

object Test14_TraitMixin {
  def main(args: Array[String]): Unit = {
    val student = new Student14
    student.study()
    student.increase()

    student.play()
    student.increase()

    student.dating()
    student.increase()

    println("====================")
    //动态混入
    val studentwithtalent = new Student14 with Talent{
      override def singing(): Unit = {
        println("He is good at singing")
      }

      override def dancing(): Unit = {
        println("He is good at dancing")
      }
    }
    studentwithtalent.sayHello()
    studentwithtalent.play()
    studentwithtalent.dating()
    studentwithtalent.dancing()
  }
}


trait Talent{
  def singing()
  def dancing()
}

//再定义一个特质
trait Knowledge {
  var amount :Int = 0
  def increase()
}

class Student14 extends Person13 with Young with Knowledge {
  //重写冲突的属性
  override val name = "student"

  //实现抽象方法
  def dating() = println(s"Student $name is dating")

  def study() = println(s"student $name is studying")

  //实现特质中的抽象方法
  override def increase(): Unit = {
    amount +=1
    println(s"student $name knowledge increased: $amount")
  }
  //重写父类方法
  override def sayHello(): Unit = {
    super.sayHello()
    println(s"Hello from:student $name")
  }
}