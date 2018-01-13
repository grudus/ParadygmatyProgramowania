package com.paradygmaty


object Launcher {

  def main(args: Array[String]): Unit = {

    val time = new Time3(66)
    println(time)
    time.minute = 34
    println(time)

    time.hour = 4
    println(time)
  }
}