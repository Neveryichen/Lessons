package Chapter6

object Test15_TraitOverlying {
  def main(args: Array[String]): Unit = {
    val student15 = new Student15
    student15.increase()

    //钻石问题特征叠加
    val myFootball = new Myfootball
    println(myFootball.describe()) //钻石问题的叠加顺序:从右往左逐渐调用
  }
}


trait Knowledge15 {
  var amount: Int = 0

  def increase() = {
    println("Knowledge increased")
  }
}

//定义球类
trait Ball {
  def describe() = "ball"
}

//定义颜色特征
trait ColorBall extends Ball {
  var color = "red"

  override def describe(): String = {
    color + "-" + super.describe()
  }
}

//定义球类种类
trait CategoryBall extends Ball {
  var category = "foot"

  override def describe(): String = {
    category + "-" + super.describe()
  }
}

//定义一个自定义球的类
class Myfootball extends CategoryBall with ColorBall {
  override def describe(): String = "My ball is a " + super.describe()
}


trait Talent15 {
  def singing()

  def dancing()

  def increase(): Unit = {
    println("Talented showed")
  }
}

class Student15 extends Person13 with Talent15 with Knowledge15 {
  override def singing(): Unit = {
    println("singing")
  }

  override def dancing(): Unit = {
    println("dancing")
  }

  override def increase(): Unit = {
    super.increase() //多方法重名冲突时,选取最后一个with的特质
  }
}