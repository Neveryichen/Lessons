package SparkCore.Requirement

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark02_Req1_HotCategoryTop10Analysis {
  def main(args: Array[String]): Unit = {
    //TODO Top10热门品类
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("HotCategoryTop10Analysis") //【*】代表根据本机自身CPU核数建立多线程，不加则默认单线程
    val sc = new SparkContext(sparkConf)

    //Q1:actionRDD重复使用
    //Q2:cogroup有可能会存在shuffle，性能可能较低


    //1.读取原始日志数据
    val actionRDD = sc.textFile("datas/user_visit_action.txt")
    actionRDD.cache()

    //2.统计品类的点击数量：（品类ID，点击数量）
    val clickActionRDD = actionRDD.filter(
      action => {
        val datas = action.split("_")
        datas(6) != "-1"
      }
    )

    val clickCountRDD = clickActionRDD.map(
      action => {
        val datas = action.split("_")
        (datas(6), 1)
      }
    ).reduceByKey(_ + _)

    //3.统计品类的点击数量：（品类ID，下单数量）
    val orderActionRDD = actionRDD.filter(
      action => {
        val datas = action.split("_")
        datas(8) != "null"
      }
    )

    //进行扁平化操作，将一个完整的字符串拆分成同样格式的多个字符串
    val orderCountRDD = orderActionRDD.flatMap(
      action => {
        val datas = action.split("_")
        val categoryId = datas(8)
        val categoryIds = categoryId.split(",")
        categoryIds.map(id => (id, 1))
      }
    ).reduceByKey(_ + _)

    //4.统计品类的点击数量：（品类ID，支付数量）
    val payActionRDD = actionRDD.filter(
      action => {
        val datas = action.split("_")
        datas(10) != "null"
      }
    )

    //进行扁平化操作，将一个完整的字符串拆分成同样格式的多个字符串
    val payCountRDD = payActionRDD.flatMap(
      action => {
        val datas = action.split("_")
        val categoryId = datas(10)
        val categoryIds = categoryId.split(",")
        categoryIds.map(id => (id, 1))
      }
    ).reduceByKey(_ + _)

    //5.将品类进行排序，并且取前十名
    //  点击数量排序，下单数量排序，支付数量排序
    //  元组排序：先比较第一个，再比较第二个，再比较第三个，以此类推
    //  使用元组排序，则需要将前三步统计的数量集合起来变成一个元组
    val clickRDD = clickCountRDD.map {
      case (cid, cnt) => {
        (cid, (cnt, 0, 0))
      }
    }

    val orderRDD = orderCountRDD.map {
      case (cid, cnt) => {
        (cid, (0, cnt, 0))
      }
    }

    val payRDD = payCountRDD.map {
      case (cid, cnt) => {
        (cid, (0, 0, cnt))
      }
    }

    //将三个数据源合并在一起，统一进行聚合计算
    val sourceRDD: RDD[(String, (Int, Int, Int))] = clickRDD.union(orderRDD).union(payRDD)

    val analysisRDD = sourceRDD.reduceByKey(
      (t1, t2) => {
        (t1._1 + t2._1, t1._2 + t2._2, t1._3+ t2._3)
      }
    )

    val resultRDD = analysisRDD.sortBy(_._2, ascending = false).take(10)

    //6.将结果采集到控制台打印出来

    resultRDD.foreach(println)
    sc.stop()

  }
}
