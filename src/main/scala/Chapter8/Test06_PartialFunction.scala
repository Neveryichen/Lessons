package Chapter8

object Test06_PartialFunction {
  def main(args: Array[String]): Unit = {
    val list = List(("a", 12), ("b", 35), ("c", 27), ("a", 100))

    //1.map转换，实现key不变，value2倍
    val newList = list.map(tuple => (tuple._1, tuple._2 * 2))

    //2.+3.用模式匹配对元组元素赋值，实现功能，此为省略语法
    val newList2 = list.map {
      case (word, count) => (word, count * 2)
    }

    println(newList)
    println(newList2)

    //偏函数的应用，求绝对值
    //对输入数据分为不同的情形：正、负、零
    val positiveAbs: PartialFunction[Int, Int] = {
      case x if x > 0 => x
    }
    val negativeAbs: PartialFunction[Int, Int] = {
      case x if x < 0 => -x
    }
    val zeroAbs: PartialFunction[Int, Int] = {
      case x if x == 0 => 0
    }

    def abs(x: Int): Int = (positiveAbs orElse negativeAbs orElse zeroAbs) (x)
    println(abs(-67))
  }
}
