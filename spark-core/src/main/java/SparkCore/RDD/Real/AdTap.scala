package SparkCore.RDD.Real

import org.apache.spark.{SparkConf, SparkContext}

object AdTap {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("Operator") //【*】代表根据本机自身CPU核数建立多线程，不加则默认单线程
    val sc = new SparkContext(sparkConf)

    //TODO 案例实操

    //1.获取原始数据：时间戳，省份，城市，用户，广告
    var dataRDD = sc.textFile("datas/agent.log")

    //2.将原始数据进行结构的转换，方便统计
    /*
        时间戳，省份，城市，用户，广告
          =》
        （（省份，广告），1）
     */
    val mapRDD = dataRDD.map(
      line => {
        val datas = line.split(" ")
        ((datas(1), datas(4)), 1)
      }
    )


    //3.将转换结构后的数据，进行分组聚合
    //（（省份，广告），1）=> （（省份，广告），sum）
    val reduceRDD = mapRDD.reduceByKey(_ + _)

    //4.将聚合的结果进行结构的转换
    //（（省份，广告），sum）=> （省份，（广告，sum））
    val mapRDD2 = reduceRDD.map {
      case ((prv, ad), sum) => {
        (prv, (ad, sum))
      }
    }

    //5.将转换结构后的数据根据省份进行分组
    // （省份，【（广告A，sumA），（广告B，sumB）】
    val groupRDD = mapRDD2.groupByKey()

    //6.将分组后的数据组内排序（降序排序，取前三名）
    var resultRDD = groupRDD.mapValues(
      iter=>{
        iter.toList.sortBy(_._2)(Ordering.Int.reverse).take(3)
      }
    )

    //7.采集数据并打印
    resultRDD.collect().foreach(println)

    sc.stop()
  }
}
