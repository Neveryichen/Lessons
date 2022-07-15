package Chapter6

object Test04_ClassForAccess {

}

//定义一个父类
class person{
  private var idCard = "123456"
  protected var name = "alice"
  var sex = "female"
  private [Chapter6] var age = 18

  def printInfo()={
    println(s"Person: $idCard $name $sex $age")
  }
}