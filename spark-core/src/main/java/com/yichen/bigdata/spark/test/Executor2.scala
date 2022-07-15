package com.yichen.bigdata.spark.test

import java.io.ObjectInputStream
import java.net.{ServerSocket, Socket}

object Executor2 {
  def main(args: Array[String]): Unit = {
    //启动服务器，接收数据
    val server = new ServerSocket(8888)
    println("服务器启动，等待接收数据")

    //等待客户端连接
    val client: Socket = server.accept()
    val in = client.getInputStream
    val objIn = new ObjectInputStream(in)

    val task:SubTask = objIn.readObject().asInstanceOf[SubTask]
    val ints = task.compute()

    println("计算节点[8888]计算的结果为：" + ints)

    objIn.close()
    client.close()
    server.close()
  }
}
