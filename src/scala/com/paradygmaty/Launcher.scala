package com.paradygmaty


object Launcher {
  def main(args: Array[String]): Unit = {
    List1

    val list = List(1, 2, 3)
    val list2 = 4 :: 5 :: 6 :: 7 :: List()

    val list4 = 4 :: list

    val list5 = list ::: list2

    println(list)
    println(list2)
    println(list4)
    println(list5)
  }
}