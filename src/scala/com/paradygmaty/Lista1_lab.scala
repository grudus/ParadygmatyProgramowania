package com.paradygmaty

object Lista1_lab {


  /*Napisz funkcję, która przyjmuje na wejściu tablicę liczb rzeczywistych, a następnie zwraca sumę tych
  liczb. Proszę przygotować minimalny zestaw testów dla tej funkcji prezentujący jej działanie. (Pusta
  tablica to też tablica */

  def sum(list: List[Double]): Double = {
    if (list == Nil) 0
    else list.head + sum(list.tail)
  }

  println(sum(List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)))
  println(sum(List(1.1, -1.1, 5.55)))
  println(sum(List()))
  println(sum(Nil))

  /*Napisz funkcję, która przyjmuje dwa parametry: tablicę liczb całkowitych i liczbę całkowitą (n).
  ma ona zwracać tablicę tylko z liczbami mniejszymi niż zadana liczba n, które znajdowały się w tablicy
  będącej pierwszym parametrem*/

  def filterLowerElements(elem: Int, list: List[Int]): List[Int] = {
    if (list == Nil) Nil
    else if (list.head < elem) list.head :: filterLowerElements(elem, list.tail) else filterLowerElements(elem, list.tail)
  }

  println(filterLowerElements(10, List(5, 7, 12, 155, 1)))
  println(filterLowerElements(10, List(12, 12, 13)))
  println(filterLowerElements(10, List(1, 1, -14)))
  println(filterLowerElements(10, List()))

  /*Napisz funkcję, która przyjmuje dwa parametry: liczbę całkowitą (n) i tablicę liczb całkowitych. Ma
  ona zwracać liczbę wystąpień danej liczby w tablicy liczb.*/

  def countOccurrences(elem: Int, list: List[Int]): Int = {
    if (list == Nil) 0
    else (if (elem == list.head) 1 else 0) + countOccurrences(elem, list.tail)
  }

  println(countOccurrences(1, List(1, 1, 1, 2, 3, 4, 1, 2, 1)))
  println(countOccurrences(1, List(1)))
  println(countOccurrences(1, List(5, 5, 5)))
  println(countOccurrences(1, List(6)))
  println(countOccurrences(1, List()))


}
