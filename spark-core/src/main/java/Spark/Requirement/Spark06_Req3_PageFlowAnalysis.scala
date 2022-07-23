package Spark.Requirement

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark06_Req3_PageFlowAnalysis {
  def main(args: Array[String]): Unit = {
    //TODO Top10热门品类
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("HotCategoryTop10Analysis") //【*】代表根据本机自身CPU核数建立多线程，不加则默认单线程
    val sc = new SparkContext(sparkConf)

    val actionRDD = sc.textFile("datas/user_visit_action.txt")
    val actionDataRDD = actionRDD.map(
      action => {
        val datas = action.split("_")
        UserVisitAction(
          datas(0),
          datas(1).toLong,
          datas(2),
          datas(3).toLong,
          datas(4),
          datas(5),
          datas(6).toLong,
          datas(7).toLong,
          datas(8),
          datas(9),
          datas(10),
          datas(11),
          datas(12).toLong,
        )
      }
    )
    actionDataRDD.cache()

    //TODO 对指定的页面连续跳转进行统计
    val IDs = List(1,2,3,4,5,6,7)
    val okFlowIds = IDs.zip(IDs.tail)
    //TODO 计算分母
    val pageIDtoCount = actionDataRDD.filter(
      action=>{
        IDs.init.contains(action.page_id)
      }
    ).map(
      action => {
        (action.page_id, 1)
      }
    ).reduceByKey(_ + _).collect().toMap

    //TODO 计算分子

    //根据session进行分组
    val sessionRDD = actionDataRDD.groupBy(_.session_id)

    //分组后根据访问时间进行排序（升序）
    val mapValueRDD = sessionRDD.mapValues(
      iter => {
        val sortList = iter.toList.sortBy(_.action_time)
        val flowIDs = sortList.map(_.page_id)
        //Sliding方法：滑窗
        //zip方法：拉链
        val pageFlowIDs = flowIDs.zip(flowIDs.tail)

        //将不合法的页面跳转进行过滤
        pageFlowIDs.filter(
          t=>{
            okFlowIds.contains(t)
          }
        ).map(
          t => {
            (t, 1)
          }
        )
      }
    )

    //((1,2),1)
    val flatRDD = mapValueRDD.map(_._2).flatMap(list => list)
    //((1,2),sum)
    val dataRDD = flatRDD.reduceByKey(_ + _)

    //TODO 计算单跳转换率
    //分子除以分母
    dataRDD.foreach {
      case ((pageID1, pageID2), sum) => {
        val denominator = pageIDtoCount.getOrElse(pageID1, 0)
        println(s"页面${pageID1}跳转到${pageID2}的单跳转换率为：" + (sum.toDouble/denominator))
      }
    }
    sc.stop()
  }

  //用户访问动作表
  case class UserVisitAction(
                              date: String, //用户点击行为的日期
                              user_id: Long, //用户的 ID
                              session_id: String, //Session 的 ID
                              page_id: Long, //某个页面的 ID
                              action_time: String, //动作的时间点
                              search_keyword: String, //用户搜索的关键词
                              click_category_id: Long, //某一个商品品类的 ID
                              click_product_id: Long, //某一个商品的 ID
                              order_category_ids: String, //一次订单中所有品类的 ID 集合
                              order_product_ids: String, //一次订单中所有商品的 ID 集合
                              pay_category_ids: String, //一次支付中所有品类的 ID 集合
                              pay_product_ids: String, //一次支付中所有商品的 ID 集合
                              city_id: Long //城市 id
                            )
}
