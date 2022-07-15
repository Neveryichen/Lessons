package Chapter9Plus

object Test02_Implicit {
  def main(args: Array[String]): Unit = {
    //val new12 = new MyRichInt(12)


    //1.隐式函数
    //implicit def convert(num:Int):MyRichInt = new MyRichInt(num)


    //2.隐式类
    implicit class MyRichInt2(val self: Int) {
      //自定义比较大小的方法
      def myMax2(n: Int): Int = if (n < self) self else n

      def myMin2(n: Int): Int = if (n < self) n else self
    }
    println(12.myMax2(15))

    println("=======================")

    //3.隐式参数
    implicit val str: String = "alice"  //同一作用范围内只能有一个隐式参数
    implicit val age:Int = 18
    def sayHello(implicit name: String = "alice"): Unit = {
      println("hello," + name)
    }

    def sayHi(name: String = "Bobby"): Unit = {
      println("hi, " + name)
    }

    sayHello  //省略括号表示在使用隐式值，加上括号表示在使用默认值
    sayHi()

    //简便写法
    def hiAge():Unit={
      println("hi,"+ implicitly[Int])
    }
    hiAge()

  }
}

//自定义类
//implicit class MyRichInt(val self: Int) {
//  //自定义比较大小的方法
//  def myMax(n: Int): Int = if (n < self) self else n
//
//  def myMin(n: Int): Int = if (n < self) n else self
//}