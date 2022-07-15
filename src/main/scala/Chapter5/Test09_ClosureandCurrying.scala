package Chapter5

object Test09_ClosureandCurrying {
  def main(args: Array[String]): Unit = {
    def add(a: Int, b: Int): Int = {
      a + b
    }

    //1.考虑固定一个加数的场景
    def addByFour(b: Int): Int = {
      4 + b
    }

    //2.扩展固定加数改变的情况
    def addByFive(b: Int): Int = {
      5 + b
    }

    //3.将固定加数作为另一个参数传入，但是是作为”第一层参数“传入
    def addByFour1(): Int => Int = { //此时a的值在函数中固定
      val a = 4

      def addB(b: Int): Int = {
        a + b
      }

      addB
    }

    def addByA(a: Int): Int => Int = { //运用addByA可以自由决定固定加数的值
      def addB(b: Int): Int = {
        a + b
      }

      addB
    }

    println(addByA(35)(24))

    val addByfour2 = addByA(4)
    val addBySix = addByA(6)

    println(addBySix(24))

    //4.lambda表达式的简写
    def addByA1(a: Int): Int => Int = a + _

    val addBySix2 = addByA(6)

    //5.柯里化
    def addCurrying(a: Int)(b: Int): Int = {
      a + b
    }
  }
}
