package Chapter5

object Test08_FunctionPractice {
  def main(args: Array[String]): Unit = {

    def judge(a: Int, b: String, c: Char): Boolean = {
      if (a == 0 && b == "" && c == '0') {
        false
      }
      else
        true
    }

    val fun = judge _

    println(fun(0, "", '0'))
    println(fun(1, "yichen", 'y'))

    println("=========================================")

    def func(a: Int): String => Char => Boolean = {
      def f1(b: String): Char => Boolean = {
        def f2(c: Char): Boolean = {
          if (a == 0 && b == "" && c == '0') false else true
        }

        f2
      }

      f1
    }

    println(func(0)("")('0'))

    //匿名函数简写
    def func2(i: Int): String => (Char => Boolean) = {
      s => c => if (i == 0 && s == "" && c == '0') false else true
    }

    //柯里化
    def func3(i:Int)(s:String)(c:Char):Boolean={
      if (i == 0 && s == "" && c == '0') false else true
    }


  }
}
