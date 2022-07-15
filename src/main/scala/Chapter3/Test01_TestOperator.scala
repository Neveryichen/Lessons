package Chapter3

object Test01_TestOperator {
  def main(args: Array[String]): Unit = {
    //1.算术运算符
    val result1 = 10 / 3
    println(result1)

    val result2: Double = 10 / 3
    println(result2)

    val result3: Double = 10.0 / 3
    println(result3.formatted("%5.2f"))

    val result4 = 10 % 3
    println(result4)

    //2.比较运算符
    val s1: String = "hello"
    val s2: String = new String("hello")

    println(s1 == s2)
    println(s1.equals(s2)) //判断值是否相同
    println(s1.eq(s2)) //判断引用地址是否相同

    //3.逻辑运算符
    println("=========================")

    def m(n: Int): Int = {
      println("m被调用")
      return n
    }

    val n = 1
    println((4 > 5) && m(n) > 0) //“&&”后的语句不会被计算

    //判断一个字符串是否为空
    def isNotEmpty(str: String): Boolean = {
      return str != null && !("".equals(str.trim))
    }

    println(isNotEmpty(null))

    //4.赋值运算符
    var b: Byte = 10
    var i: Int = 10
    i += 1
    println(i)

    //5.运算符的本质
    val n1: Int = 12
    val n2 = 37

    println(n1.+(n2))
    println(n1 + n2)//本质上是方法调用，将’.‘省略，再将括号省略


  }
}
