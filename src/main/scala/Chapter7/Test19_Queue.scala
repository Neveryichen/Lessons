package Chapter7

import scala.collection.immutable.Queue
import scala.collection.mutable
import scala.collection.parallel.immutable

object Test19_Queue {
  def main(args: Array[String]): Unit = {
   //创建一个可变队列
   val queue = new mutable.Queue[String]()

    queue.enqueue("a","b","c")
    println(queue)
    println(queue.dequeue())
    println(queue)
    println(queue.dequeue())
    println(queue)
    println(queue.dequeue())

    queue.enqueue("d","e")
    println(queue.dequeue())
    println(queue)

    println("-----------------------------")
    //不可变队列
    val queue2 = Queue("a","b","c")
    val queue3 = queue2.enqueue("d")
    println(queue2)
    println(queue3)
  }
}
