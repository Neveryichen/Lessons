package SparkSql

import org.apache.spark.SparkConf
import org.apache.spark.sql.expressions.Aggregator
import org.apache.spark.sql.{Dataset, Encoder, Encoders, SaveMode, SparkSession}

object Spark04_SparkSQL_JDBC {
  def main(args: Array[String]): Unit = {

    //TODO 创建SparkSQL的运行环境
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("SparkSQL")
    val spark = SparkSession.builder().config(sparkConf).getOrCreate()
    import spark.implicits._


    //读取MySQL数据
    val df = spark.read
      .format("jdbc")
      .option("url", "jdbc:mysql://192.168.3.20:3306/user")
      .option("driver", "com.mysql.jdbc.Driver")
      .option("user", "root")
      .option("password", "0000")
      .option("dbtable", "test")
      .load()
      //.show

    //保存MySQL数据
    df.write
            .format("jdbc")
            .option("url", "jdbc:mysql://192.168.3.20:3306/user")
            .option("driver", "com.mysql.jdbc.Driver")
            .option("user", "root")
            .option("password", "0000")
            .option("dbtable", "test2")
            .mode(SaveMode.Append)
            .save()





    //TODO 关闭环境
    spark.close()
  }
}
