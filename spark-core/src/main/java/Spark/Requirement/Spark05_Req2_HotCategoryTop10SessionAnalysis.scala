package Spark.Requirement

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark05_Req2_HotCategoryTop10SessionAnalysis {
  def main(args: Array[String]): Unit = {
    //TODO Top10热门品类
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("HotCategoryTop10Analysis") //【*】代表根据本机自身CPU核数建立多线程，不加则默认单线程
    val sc = new SparkContext(sparkConf)

    val actionRDD = sc.textFile("datas/user_visit_action.txt")
    actionRDD.cache()

    val top10Ids = Top10Category(actionRDD)

    //1.过滤原始数据，保留点击和前十品类ID
    val filterActionRDD = actionRDD.filter(
      action => {
        val datas = action.split("_")
        if (datas(6) != "-1") {
          top10Ids.contains(datas(6))
        } else {
          false
        }
      }
    )

    //2.根据品类ID和sessionID进行点击量的统计
    val reduceRDD = filterActionRDD.map(
      action => {
        val datas = action.split("_")
        ((datas(6), datas(2)), 1)
      }
    ).reduceByKey(_ + _)

    //3.将统计的结果进行结构的转换
    //（（品类ID，sessionID），sum）=> (品类ID，（sessionID，sum））
    val mapRDD = reduceRDD.map {
      case ((cid, sid), sum) => {
        (cid, (sid, sum))
      }
    }

    //4.相同的品类进行分组
    val groupRDD = mapRDD.groupByKey()

    //5.将分组后的数据进行点击量排序，取前十名
    val resultRDD = groupRDD.mapValues(
      iter => {
        iter.toList.sortBy(_._2)(Ordering.Int.reverse).take(10)
      }
    )

    resultRDD.collect().foreach(println)
  }

  def Top10Category(actionRDD:RDD[String])={
    val flatRDD = actionRDD.flatMap(
      action => {
        val datas = action.split("_")
        if (datas(6) != "-1") {
          //点击的场合
          List((datas(6), (1, 0, 0)))
        } else if (datas(8) != "null") {
          //下单的场合
          val ids = datas(8).split(",")
          ids.map(id => (id, (0, 1, 0)))
        } else if (datas(10) != "null") {
          //支付的场合
          val ids = datas(10).split(",")
          ids.map(id => (id, (0, 0, 1)))
        } else {
          Nil
        }
      }
    )

    val analysisRDD = flatRDD.reduceByKey(
      (t1, t2) => {
        (t1._1 + t2._1, t1._2 + t2._2, t1._3 + t2._3)
      }
    )

   analysisRDD.sortBy(_._2, false).take(10).map(_._1)

  }
}
