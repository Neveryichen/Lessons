package Chapter7

import scala.collection.mutable

object Test07_MutableSet {
  def main(args: Array[String]): Unit = {
    //1.创建集合
    val set1: mutable.Set[Int] = mutable.Set(13, 23, 53, 12, 13, 23, 53)
    println(set1)

    println("-----------------------")

    //2.添加元素
    val set2 = set1 + 11
    println(set2)

    set1 += 11
    println(set1)

    set1.add(10) //add和remove函数都返回一个布尔值
    println(set1)

    //3.删除元素
    val set5 = set1 - 13
    println(set5)

    set1 -= 11

    set1.remove(11)

    //4.合并两个set
    val set3 = mutable.Set(13, 23, 53, 12, 13, 23, 53)
    println(set1)
    println(set3)

    val set4 = set1 ++ set3
    println(set4)

    set1 ++= set3
    println(set1)



  }
}
