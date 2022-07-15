package Chapter5

import javax.xml.transform.Result
import scala.annotation.tailrec

object Test10_Recursion {
  def main(args: Array[String]): Unit = {
    println(fact(5))
  }

  //递归实现计算阶乘
  def fact(n: Int): Int = {
    if (n == 0) return 1
    return fact(n - 1) * n
  }

  //尾递归实现
  def tailFact(n: Int): Int = {
    @tailrec  //如果写出的代码不是尾递归，则会报错
    def loop(n: Int, currRes: Int): Int = {
      if (n == 0) return currRes
      loop(n - 1, currRes * n)
    }
    loop(n, 1)
  }

}
