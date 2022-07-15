package Chapter4

//打印输出金字塔
object Test04_Practice_Pyramid {
  def main(args: Array[String]): Unit = {
    for (i <- 1 to 9; j = 9 - i) {
      print(" "*j)
      print("*"*(2*i-1))
      println("\t")
    }
  }
}
