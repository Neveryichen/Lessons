package Chapter7

import scala.collection.mutable.ListBuffer

object Test05_ListBuffer {
  def main(args: Array[String]): Unit = {
    //1.创建可变列表
    val list1 = new ListBuffer[Int]()
    val list2 = ListBuffer(12, 53, 75)

    println(list1)
    println(list2)

    println("----------------")

    //2.添加元素
    list1.append(15, 62)
    list2.prepend(20)
    list1.insert(1, 50, 22)

    println(list1)
    println(list2)

    println("---------------------")

    list1 += 25 += 11
    31 +=: 96 +=: list1 += 25 += 11
    println(list1)

    //3.合并list
    val list3 = list1 ++ list2
    println(list3)

    println("------------------------")
    list1 ++= list2 //改变了list1
    list1 ++=: list2  //改变了list2
    println(list1)
    println(list2)

    //4.修改元素
    list2(3) = 30
    println(list2)

    //5.删除元素
    list2.remove(2)
    list2 -= 25
    println(list2)
  }
}
