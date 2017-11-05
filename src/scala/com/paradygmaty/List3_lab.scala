package com.paradygmaty

import scala.annotation.tailrec

object List3_lab {
  /*zdefiniuj funkcje przeksztalcajaca liste cyfr binarnych [0;1] na liczbe dziesietna.
  * Funkcja ma miec jeden parametr
  *
  * [1;0;1] -> 5*/

  def fromBinary(list: List[Int]): Int = {
    def binary(counter: Int, list: List[Int]): Int = {
      (counter, list) match {
        case (0, h :: Nil) => h
        case (i, h :: t) => Math.pow(2, i).toInt * h + binary(i - 1, t)
      }
    }

    binary(list.size - 1, list)
  }

  println(fromBinary(List(1, 0, 1, 0)))

  def zigi(list: List[Int]): Int = {
    list.foldLeft((0, 1))()

  }

}