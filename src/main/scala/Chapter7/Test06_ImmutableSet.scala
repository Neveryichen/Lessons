package Chapter7

object Test06_ImmutableSet {
  def main(args: Array[String]): Unit = {
    //1.创建集合
    val set1 = Set(13, 23, 53, 12, 13, 23, 53)
    println(set1)

    println("-----------------------")

    //2.添加元素
    val set2 = set1.+(20) //  化简后： set1 + 20
    println(set1)
    println(set2)

    println("-------------------------")

    //3.合并set
    val set3 = Set(13, 23, 53, 59, 80, 75)
    val set4 = set2 ++ set3
    println(set4)

    //4.删除元素
    val set5 = set3 - 13
    println(set5)
  }
}
