package Chapter7

object Test10_Tuple {
  def main(args: Array[String]): Unit = {
    //1.创建元组
    val tuple: (String, Int, Char, Boolean) = ("hello", 100, 'a', true)
    println(tuple)

    //2.访问数据
    println(tuple._1)
    println(tuple._2)

    println(tuple.productElement(0)) //此方法从0开始计数

    println("---------------------------")

    //3.遍历元组数据
    for (elem <- tuple.productIterator)
      println(elem)

    //4.嵌套元组
    val tuple1 = (12, 1.3, "tired", (23, "yichen"), 29)
    println(tuple1._4._2)
  }
}
