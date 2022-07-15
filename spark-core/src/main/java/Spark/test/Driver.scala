package Spark.test

import java.io.ObjectOutputStream
import java.net.Socket

object Driver {
  def main(args: Array[String]): Unit = {

    //连接服务器
    val client1 = new Socket("localhost", 9999)
    val client2 = new Socket("localhost", 8888)

    val task = new Task()


    val out1 = client1.getOutputStream
    val objectOut1 = new ObjectOutputStream(out1)

    val subTask = new SubTask()
    subTask.logic = task.logic
    subTask.datas = task.datas.take(2)

    objectOut1.writeObject(subTask)

    objectOut1.flush()
    objectOut1.close()
    client1.close()

    //===========================

    val out2 = client2.getOutputStream
    val objectOut2 = new ObjectOutputStream(out2)

    val subTask2 = new SubTask()
    subTask2.logic = task.logic
    subTask2.datas = task.datas.takeRight(2)

    objectOut2.writeObject(subTask2)

    objectOut2.flush()
    objectOut2.close()
    client2.close()


  }
}
