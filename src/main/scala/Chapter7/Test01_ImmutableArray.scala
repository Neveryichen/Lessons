package Chapter7

object Test01_ImmutableArray {
  def main(args: Array[String]): Unit = {
    //1.创建数组
    val arr = new Array[Int](5)
    //另一种创建方式
    val arr2 = Array(12, 37, 41, 59, 87)

    //2.访问数组中的元素
    println(arr(0))
    println(arr(1))

    arr(0) = 12
    arr(4) = 57

    //3.数组遍历
    //1).普通for循环
    for (i <- 0 until arr.length) {
      println(arr(i))
    }

    for (i <- arr.indices) {
      println(arr(i))
    }
    println("-----------------")
    //2).直接遍历所有元素，增强for循环
    for (elem <- arr2) println(elem)

    //3).迭代器
    val iter = arr2.iterator

    while (iter.hasNext)
      println(iter.next())

    //4).调用foreach方法
    arr2.foreach((elem: Int) => println(elem))

    arr.foreach(println)

    println(arr2.mkString("-")) //利用mkString也可以对数组进行遍历

    println("--------------")
    //4.添加元素
    val newarr = arr2.:+(73)
    println(newarr.mkString("-"))

    val newarr2 = newarr.+:(30)
    println(newarr2.mkString("-"))

    val newarr3 = newarr2 :+ 15
    val newarr4 = 19+: 29 +: newarr3 :+ 73
    println(newarr4.mkString("-"))
  }
}
