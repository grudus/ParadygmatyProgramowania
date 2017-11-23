package com.paradygmaty


object Launcher {

  def main(args: Array[String]): Unit = {
    val (l,r) = dupa(Stream(1,2,3,4,5,6,7))(0)

    println(l toList)
    println(r toList)
  }

  def dupa(s: Stream[Int])(index: Int): (Stream[Int], Stream[Int]) = {
    (s, index) match {
      case (Stream.Empty, _) => (Stream.Empty , Stream.Empty)
      case (h #:: t, i) => {
        val (s1, s2) = dupa (t) (i-1)
        if (i % 2 == 0) (h #:: s1, s2) else (s1, h#::s2)
      }
    }
  }
}