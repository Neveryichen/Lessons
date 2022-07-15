//用嵌套风格定义包
package com {

  import com.yichen.scala.Inner

  //在外层包中定义对象
  object Outer {
    var out:String = "out"

    def main(args: Array[String]): Unit = {
      println(Inner)
    }
  }

  package yichen {
    package scala {
      //在内层包中定义单例对象
      object Inner {
        def main(args: Array[String]): Unit = {
          println(Outer.out)
          Outer.out = "outer"
          println(Outer.out)
        }
        var in = "yichen"
      }
    }

  }

}


//在同一文件中定义多个包
package aaa{
  package bbb{

    import com.yichen.scala.Inner

    object Test01_Package{
      def main(args: Array[String]): Unit = {
        println(Inner.in)
      }
    }
  }
}