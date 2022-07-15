package Chapter7

object Test14_HighLevelFunction_Map {
  def main(args: Array[String]): Unit = {
    val list = List(1, 2, 3, 4, 5, 6, 7, 8, 9)

    //1.过滤
    //选取偶数
    val evenList = list.filter((elem: Int) => {
      elem % 2 == 0
    })
    println(evenList)

    println(list.filter(_ % 2 == 0)) //simple ver.

    println("----------------------")

    //2. map
    //把集合中每个数乘2
    println(list.map(_ * 2))
    println(list.map(x => x * x))

    println("-------------------------")

    //3.扁平化
    val nestedList: List[List[Int]] = List(List(1, 2, 3), List(4, 5), List(6, 7, 8, 9))

    val flatList = nestedList(0) ::: nestedList(1) ::: nestedList(2)
    println(flatList)

    val flatList2 = nestedList.flatten
    println(flatList2)

    println("-------------------------")

    //4.扁平映射
    //将一组字符串分词并保存成单词的链表
    val strings: List[String] = List("Hello world", "Hello scala", "Hello java", "we study")
    val splitlist: List[Array[String]] = strings.map(string => string.split(" "))
    val flattenList = splitlist.flatten
    println(flattenList)

    val flatmapList = strings.flatMap(_.split(" "))
    println(flatmapList)

    println("---------------------------")

    //5.分组 groupBy
    //fen cheng ji ou liang zu
    val groupMap = list.groupBy(_ % 2)
    println(groupMap)

    //给定一组词汇,按照单词的首字母进行分组
    val wordList = List("China","America","alice","Canada","cary","bob","Japan")
    println(wordList.groupBy(_.charAt(0)))
  }
}
