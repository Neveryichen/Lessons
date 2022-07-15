package Chapter5

object Test11_ControlAbstraction {
  def main(args: Array[String]): Unit = {
    //1.传值参数
    def f0(a: Int): Unit = {
      println("a:" + a)
      println("a:" + a)
    }

    def f1():Int={
      println("f1调用")
      15
    }

    f0(23)

    //2.传名参数.传递的不再是具体的值，而是代码块
    def f2(a: => Int): Unit = {
      println("a:" + a)
      println("a:" + a)
    }

    f2(23)
    f2(f1())
  }
}
