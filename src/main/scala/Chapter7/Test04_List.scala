package Chapter7

object Test04_List {
  def main(args: Array[String]): Unit = {
    //1.创建一个列表
    val list1 = List(23, 49, 87)
    println(list1)

    //2.关于访问和遍历元素
    println(list1(1))
    list1.foreach(println)

    //3.添加元素
    val list2 = list1.+:(10)
    val list3 = list1 :+ 23
    println(list1)
    println(list2)
    println(list3)

    val list4 = list2.::(51)
    println(list4)

    val list5 = Nil.::(13) //表示创建一个新的列表
    println(list5)

    val list6 = 32 :: 16 :: Nil
    val list7 = 17 :: 28 :: 59 :: 16 :: Nil //从左到右依次添加数字从而创建列表
    println(list7)

    val list8 = list6 :: list7  //list6整体合并，不是数字
    println(list8)

    val list9 = list6 ::: list7 //list6的所有元素都被拆开后进行合并
    println(list9)

    val list10 = list6 ++ list7 //与“：：：”调用相同方法，功能相同
    println(list10)
  }
}
