package Chapter4

object Test02_ForLoop {
  def main(args: Array[String]): Unit = {
    //java For语法: for(int i=0;i<10;i++){System.out.println(i + ".HelloWorld")

    //1.范围遍历
    for (i <- 1 to 10) {
      println(i + ".HelloWorld")
    }
    //for(i <- 1.to(10)){}    为上面for循环的本质

    println("======================================")

    for (i <- Range(1, 10)) {
      println(i + ".HelloWorld")
    }

    println("=======================================")

    for (i <- 1 until 10) {
      println(i + ".HelloWorld")
    }

    println("========================================")

    //2.集合遍历
    for (i <- Array(12, 34, 53)) {
      println(i)
    }

    println("===========================================")

    //3.循环守卫
    for (i <- 1 to 10 if i != 5) {
      println(i)
    }

    println("============================================")

    //4.循环步长
    //for(i <- 1 .to (10) .by (2))
    for (i <- 1 to 10 by 2) { //步长不能为0
      println(i)
    }

    for (i <- 13 to 30 by 3) {
      println(i)
    }

    println("------------------------")

    for (i <- 30 to 13 by -3) {
      println(i)
    }

    for (i <- 1.0 to 10.0 by 0.5) { //会出现精度缺失，不建议使用double类型时使用to
      println(i)
    }

    println("============================================")

    //5.循环嵌套
    for (i <- 1 to 3) { //传统Java形式
      for (j <- 1 to 3) {
        println(i + j)
      }
    }

    for (i <- 1 to 3; j <- 1 to 3) { //Scala可以采用特殊形式
      println("i:" + i + " " + "j:" + j)
    }

    println("==========================================")

    //6.循环引入变量
    for (i <- 1 to 10) {
      val j = 10 - i
      println(i + j)
    }

    for (i <- 1 to 10; j = 10 - i) { //Scala的快捷写法
      println(i + j)
    }

    for { //可以使用花括号书写，此时可以换行
      i <- 1 to 10
      j = 10 - i
    } {
      println(i + j)
    }

    println("================================")

    //7.循环返回值
    //默认for循环返回值一直为Unit
    val a: Unit = for (i <- 1 to 10) {
      println(i)
    }
    println("a = " + a)

    val b = for (i <- 1 to 10) yield i*i
    println("b=" + b)

    println("===================================")


  }
}
