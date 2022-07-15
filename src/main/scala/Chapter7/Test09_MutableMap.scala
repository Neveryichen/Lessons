package Chapter7

import scala.collection.mutable

object Test09_MutableMap {
  def main(args: Array[String]): Unit = {
    //1.创建Map
    val map1: mutable.Map[String, Int] = mutable.Map("a" -> 13, "b" -> 25, "hello" -> 3)
    println(map1)
    println(map1.getClass)

    //2.添加元素
    map1.put("c", 5)
    map1.put("d", 9)

    map1 += (("e", 7))
    println(map1)

    println("------------------------")

    //3.删除元素
    map1.remove("c")
    map1 += (("e", 7))
    println(map1.getOrElse("c", 0))

    map1 -= "d"
    println(map1)

    println("------------------------")

    //4.修改元素
    map1.update("c", 5)
    map1.update("e", 55)

    println(map1)

    println("------------------------")

    //5.合并两个Map
    val map2 = mutable.Map("a" -> 10, "b" -> 25, "hello" -> 5)
    map1 ++= map2 //map2的值覆盖掉map1
    println(map1)
  }
}
