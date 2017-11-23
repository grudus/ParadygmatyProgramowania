package com.paradygmaty

import com.paradygmaty.List4_lab.Expression

object Lab6 {

  /*(Scala) Jedna z pętli w języku Scala ma następującą składnię: while (warunek) wyrażenie. Napisz
w Scali funkcję whileLoop (bez używania efektów obliczeniowych), która pobiera dwa argumenty:
warunek oraz wyrażenie i dokładnie symuluje działanie pętli while (również składniowo). Jakiego
typu (i dlaczego) muszą być argumenty i wynik funkcji?*/

  def whileLoop(condition: => Boolean)(expression: => Unit): Unit = {
    if (condition) {
      expression
      whileLoop(condition)(expression)
    }
  }

  def whileLoop2(condition: () => Boolean)(expression: () => Unit): Unit = {
    if (condition()) {
      expression()
      whileLoop2(condition)(expression)
    }
  }

  def dupa(i: Int)(b: Int) = {}

  dupa {
    println("hehe")
    3
  } {4}

  var i = 0
  whileLoop2(() => i < 10)(() => {
    println(i)
    i+=1
  })



  /*(Scala) Jedna z pętli w języku Scala ma następującą składnię: while (warunek) wyrażenie. Napisz
w Scali funkcję whileLoop (bez używania efektów obliczeniowych), która pobiera dwa argumenty:
warunek oraz wyrażenie i dokładnie symuluje działanie pętli while (również składniowo). Jakiego
typu (i dlaczego) muszą być argumenty i wynik funkcji?*/

  def swap(tab: Array[Int], i: Int, j: Int): Unit = {
    val temp = tab(i)
    tab(i) = tab(j)
    tab(j) = temp
  }

  def choosePivot(tab: Array[Int], i: Int, j: Int): Int =
    tab((i + j) / 2)

  def partition(tab: Array[Int], l: Int, r: Int): (Int, Int) = {
    var (i, j, pivot) = (l, r, choosePivot(tab, l, r))
    while (i <= j) {
      while (tab(i) < pivot)
        i += 1
      while (pivot < tab(j))
        j -= 1
      if (i <= j) {
        swap(tab, i, j)
        i += 1
        j -= 1
      }
    }
    (i, j)
  }

  def quick(tab: Array[Int], l: Int, r: Int): Unit = {
    if (l < r) {
      val (i, j) = partition(tab, l, r)
      if ( (j - l) < (r - i) ) {
        quick(tab, l, j)
        quick(tab, i, r)
      }
      else {
        quick(tab, i, r)
        quick(tab, l, j)
      }
    }
    else ()
  }

  def quicksort(tab: Array[Int]): Unit =
    quick(tab, 0, tab.length - 1)

}
