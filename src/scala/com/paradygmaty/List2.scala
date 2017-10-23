package com.paradygmaty

import scala.annotation.tailrec

object List2 {

/*Jaka będzie głębokość stosu w Scali, a jaka w OCamlu dla wywołania evenR(3) (funkcja
zdefiniowana na wykładzie)?*/

  def isEven(n: Int): Boolean = if (n == 0) true else isOdd(n - 1)
  def isOdd(n: Int): Boolean = if (n == 0) false else isEven(n-1)

  /*
  * isEven (3)
  * if n==0 true else isOdd (2)
  * if n==0 false else isEven (1)
  * if n == 0 true else isOdd 0
  * false
  * */

  /*Liczby Fibonacciego są zdefiniowane następująco:
f(0) = 0
f(1) = 1
f(n+2) = f(n) + f(n+1)
Napisz dwie funkcje, które dla danego n znajdują n-tą liczbę Fibonacciego:
a) opartą bezpośrednio na powyższej definicji,
b) wykorzystującą rekursję ogonową.
Porównaj (bez mierzenia) ich szybkość wykonania, obliczając np. 42-gą liczbę
Fibonacciego.*/

  def fibonacciDefinition(n: Long): Long = {
    n match {
      case 0 => 0
      case 1 => 1
      case _ => fibonacciDefinition(n - 1) + fibonacciDefinition(n - 2)
    }
  }

  def fibonacci(n: Long): Long = {
    @tailrec def fib(counter: Long, prev: Long, next: Long): Long = {
      counter match {
        case 0 => prev
        case _ => fib(counter - 1, next, prev + next)
      }
    }

    fib(n, 0, 1)
  }

  println(fibonacciDefinition(42))
  println(fibonacci(42))

  for (i <- 0 to 50)
    println(fibonacci(i))


  /* Dla zadanej liczby rzeczywistej a oraz dokładności  można znaleźć pierwiastek
 trzeciego stopnia z a wyliczając kolejne przybliżenia xi tego pierwiastka (metoda
 Newtona-Raphsona):
 x0 = a/3 dla a > 1
 x0 = a dla a ≤ 1
 xi+1 = xi + (a/xi
2
– xi )/3
 Dokładność jest osiągnięta, jeśli |xi
3
– a|   * |a| .
 Napisz efektywną (wykorzystującą rekursję ogonową) funkcję root3, która dla zadanej
 liczby a znajduje pierwiastek trzeciego stopnia z dokładnością 10-15
.
  */

  def cubeRoot(number: Double): Double = {
    @tailrec def cubeRootEps(cube: Double): Double = {
      cube match {
        case i if Math.abs(i * i * i - number) <= 1e-15 * Math.abs(number) => cube
        case _ => cubeRootEps(cube + (number / (cube * cube) - cube) / 3)
      }
    }

    cubeRootEps(if (number > 1) number / 3 else number)
  }

  println(cubeRoot(1000*1000*1000))
  for (i <- -100 to 100)
    println(cubeRoot(i))


  /*. Zwiąż zmienną x z wartością 0 konstruując wzorce, do których mają się dopasować
następujące wyrażenia:
a) [-2; -1; 0; 1; 2]
b) [ (1,2); (0,1) ]
Np. dla wyrażenia (true,"hello",0) wymaganym wzorcem jest (_ ,_ ,x).*/

  val _ :: _ :: x :: _ = List(-2, -1, 0, 1, 2)
  val List((_, _), (x2, _)) = List((1, 2), (0, 1))


  /*. Zdefiniuj funkcję initSegment : 'a list * 'a list -> bool sprawdzającą w czasie liniowym, czy
pierwsza lista stanowi początkowy segment drugiej listy. Każda lista jest swoim
początkowym segmentem, lista pusta jest początkowym segmentem każdej listy.*/


  @tailrec def initSegment[A](list1: List[A], list2: List[A]): Boolean = {
    (list1, list2) match {
      case (Nil, _) => true
      case (h1 :: t1, h2 :: t2) if h1 == h2 => initSegment(t1, t2)
      case _ => false
    }
  }


  println(initSegment(List(1, 2, 3), List(1, 2, 3, 4)))
  println(initSegment(List(1, 2, 3, 4), List(1, 2, 3, 4)))
  println(initSegment(List(1, 2, 3, 4, 5), List(1, 2, 3, 4)))
  println(initSegment(List(), List(1, 2, 3, 4)))
  println(initSegment(List(), List()))
  println(initSegment(List(1, 2), Nil))


  /*6. a) Zdefiniuj funkcję replaceNth : 'a list * int* 'a -> 'a list, zastępującą n-ty element listy
podaną wartością (pierwszy element ma numer 0), np.
replaceNth (['o'; 'l'; 'a'] ,1, 's') => ['o'; 's'; 'a']
Nie wykorzystuj żadnej funkcji bibliotecznej!
b) Jaka jest złożoność obliczeniowa tej funkcji? Zilustruj rysunkiem (patrz wykład str.37-
40) reprezentację obu lis*/

  def replaceNth[A](list: List[A], index: Int, elem: A): List[A] = {
    list match {
      case Nil => Nil
      case _ :: t if index == 0 => elem :: t
      case h :: t => h :: replaceNth(t, index - 1, elem)
    }
  }

  println(replaceNth(List("a", "b", "c", "f"), 0, "dupa"))
  println(replaceNth(List("a", "b", "c", "f"), 1, "dupa"))
  println(replaceNth(List("a", "b", "c", "f"), 2, "dupa"))
  println(replaceNth(List("a", "b", "c", "f"), 3, "dupa"))
  println(replaceNth(List("a", "b", "c", "f"), 4, "dupa"))
  println(replaceNth(Nil, 4, "dupa"))

}
