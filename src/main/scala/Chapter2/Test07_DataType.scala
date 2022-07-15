package Chapter2

import Chapter1.Student

object Test07_DataType {
  def main(args: Array[String]): Unit = {
    //1.整数类型
    val a1:Byte = 127

    val a3 = 12 //整数的默认类型为int
    val a4 = 26156481515684L// 长整型数值定义，数字过大必须进行此操作

    val b1:Byte=10
    val b2:Byte=10+20//即使报错但是在范围内，则可以使用
//    val b3:Byte=(b1+20)//不可以使用这类操作
    val b3:Byte = (b1+20).toByte//数据类型转换

    //2.浮点类型
    val f1 = 1.234f

    //3.字符类型
    val c1:Char = 'a'

    val c2:Char = '9'

    //特殊字符
    val c3:Char = '\t'//制表符
    val c4:Char = '\n'//换行符

    //转义字符
    val c5 = '\\' // 表示\自身
    val c6 = '\"' //表示”

    //字符变量底层保存ASCII码
    val i1:Int = c1

    val i2:Int = c2

    val c7:Char = (i1+1).toChar

    //4.布尔类型
    val isTrue:Boolean = true

    //5.空类型
      //5.1 空值Unit
      def m1():Unit = {
        println("m1被调用执行")
      }

      val a = m1()
      println(a)

      //5.2 空引用Null
      var student = new Student("alice",20)
      student = null
    println(student)

      //5.3 Nothing
      def m2(n:Int): Int = {
        if(n==0)
          throw new NullPointerException
        else
        return n
      }

      val b = m2(2)
      println(b)
  }
}
