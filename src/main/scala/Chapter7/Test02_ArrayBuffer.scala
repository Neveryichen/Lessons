package Chapter7

import scala.collection.mutable.ArrayBuffer

object Test02_ArrayBuffer {
  def main(args: Array[String]): Unit = {
    //1.创建可变数组
    val arr1: ArrayBuffer[Int] = new ArrayBuffer[Int]()
    val arr2 = ArrayBuffer(23, 45, 85)

    println(arr1.mkString("-"))
    println(arr2)

    //2.访问数组中的元素
    println(arr2(1))
    arr2(1) = 39
    println(arr2(1))

    println("------------------")

    //3.添加元素
    val newarr1 = arr1 :+ 15
    println(newarr1)

    arr1 += 19
    println(arr1)

    77 +=: arr1

    arr1.append(36)
    arr1.prepend(11, 76)
    arr1.insert(1, 13, 59)
    println(arr1)

    arr1.insertAll(1, newarr1)
    arr1.appendAll(newarr1)
    arr1.prependAll(newarr1)
    println(arr1)

    //4.删除元素
    arr1.remove(3)
    println(arr1)

    arr1.remove(0, 3)
    println(arr1)

    arr1 -= 14 //先遍历搜索是否有14，有则删除没有则无变化
    println(arr1)

    //5.可变数组转换为不可变数组
    val arr = ArrayBuffer(23,59,41)
    val newarr = arr.toArray
    println(newarr.mkString("-"))
    println(arr)

    //6.不可变数组转换为不可变数组
    val buffer = newarr.toBuffer
    println(buffer)
  }
}
