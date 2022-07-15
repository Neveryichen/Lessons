package Chapter4

object Test05_WhileLoop {
  def main(args: Array[String]): Unit = {
    //while
    var a = 10
    while (a >= 1) {
      println("this is a while loop:" + a)
      a -= 1
    }
    //do while
    var b = 0
    do {
      println("this is a while loop too" + b)
      b -= 1
    } while (b > 0)
  }
}
