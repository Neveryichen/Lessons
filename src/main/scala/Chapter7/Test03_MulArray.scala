package Chapter7

object Test03_MulArray {
  def main(args: Array[String]): Unit = {
    //1.创建二维数组
    val array = Array.ofDim[Int](2, 3)

    //2.访问元素
    array(0)(2) = 19
    array(1)(0) = 25

    for(i<- array.indices;j<- array(i).indices){
      println(array(i)(j))
    }

    for(i<- array.indices;j<- array(i).indices){
      print(array(i)(j) + "\t")
      if(j == array(i).length - 1){
        println()
      }
    }

    array.foreach(line => line.foreach(print))
    array.foreach((_.foreach(println)))
  }
}
