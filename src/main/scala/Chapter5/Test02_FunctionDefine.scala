package Chapter5

object Test02_FunctionDefine {
  def main(args: Array[String]): Unit = {

    //    （1）函数 1：无参，无返回值
    def f1(): Unit = {
      println("1.无参，无返回值")
    }

    f1()

    println("====================")

    //    （2）函数 2：无参，有返回值
    def f2(): Int = {
      println("2.无参，有返回值")
      return 2
    }

    println(f2())

    println("====================")

    //    （3）函数 3：有参，无返回值
    def f3(name: String): Unit = {
      println("3.有参，无返回值")
    }

    println(f3("alice"))

    println("====================")

    //    （4）函数 4：有参，有返回值
    def f4(name: String): String = {
      println("4.有参，有返回值")
      return "Hi," + name
    }

    println(f4("Bob"))

    println("===================")

    //    （5）函数 5：多参，无返回值
    def f5(name1: String, name2: String): Unit = {
      println("5.多参，无返回值")
      println(s"$name1 和 $name2 都是我的好朋友")
    }

    f5("alice", "bob")

    println("=====================")

    //    （6）函数 6：多参，有返回值
    def f6(a: Int, b: Int): Int = {
      println("6.多参，有返回值")
      return a + b
    }

    println(f6(5, 6))
  }
}
