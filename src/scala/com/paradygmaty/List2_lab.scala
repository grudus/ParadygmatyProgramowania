package com.paradygmaty

import scala.annotation.tailrec

object List2_lab {

/*Napisać funkcję zwracającą N-ty wyraz ciągu Fibonacciego. (rekurencja ogonowa -3 pkt, zwykła -1pkt*/

  def fibonacci(n: Long): Long = {
    @tailrec def fib2(counter: Long, prev: Long, next: Long): Long = {
      counter match {
        case 0 => next
        case _ => fib2(counter - 1, next, prev + next)
      }
    }
    fib2(n, 0, 1)
  }

  println (fibonacci(0))
  println (fibonacci(1))
  println (fibonacci(2))
  println (fibonacci(3))
  println (fibonacci(4))
  println (fibonacci(5))
  println (fibonacci(6))
  println (fibonacci(10000000))

}
