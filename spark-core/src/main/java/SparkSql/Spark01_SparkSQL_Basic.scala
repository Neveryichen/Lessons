package SparkSql

import org.apache.spark.SparkConf
import org.apache.spark.sql.{DataFrame, SparkSession}

object Spark01_SparkSQL_Basic {
  def main(args: Array[String]): Unit = {

    //TODO 创建SparkSQL的运行环境
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("SparkSQL")
    val spark = SparkSession.builder().config(sparkConf).getOrCreate()
    import spark.implicits._

    //TODO 执行逻辑操作

    //DataFrame
    //val df = spark.read.json("datas/user.json")
    //df.show()

    //DataFrame => SQL
    //df.createOrReplaceTempView("user")

    //spark.sql("select * from user").show()

    //DataFrame => DSL
    //在使用DataFrame时，如果涉及到转换操作，需要引入转换规则
    //df.select("age","username").show()
    //df.select($"age"+1).show()//df.select('age+1).show()为其简写形式

    //TODO DataSet
    //val seq = Seq(1,2,3,4)
    //val ds = seq.toDS()
    //ds.show()

    //RDD <=> DataFrame
    val rdd = spark.sparkContext.makeRDD(List((1, "zhangsan", 30), (2, "lisi", 40)))
    val df: DataFrame = rdd.toDF("id", "name", "age")
    val rowRDD = df.rdd

    //DataFrame <=> DataSet
    val ds = df.as[User]
    val DF = ds.toDF()

    //RDD <=> DataSet
    val DS = rdd.map {
      case (id, name, age) => {
        User(id, name, age)
      }
    }.toDS()

    val UserRDD = DS.rdd

    //TODO 关闭环境
      spark.close()
  }

  case class User(id: Int, name: String, age: Int)
}
