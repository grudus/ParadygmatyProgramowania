package com.paradygmaty

object List3 {
  /*2. Zdefiniuj funkcje a) curry3 i b) uncurry3, przeprowadzające konwersję między zwiniętymi
  i rozwiniętymi postaciami funkcji od trzech argumentów. Podaj ich typy.*/

  def curry3[A, B, C, X](f: (A, B, C) => X): A => B => C => X = {
    x => y => z => f(x, y, z)
  }

  def unCurry3[A, B, C, X](f: A => B => C => X): (A, B, C) => X = {
    (x, y, z) => f(x)(y)(z)
  }

//  nie kuried add
  def add(a: Int, b: Int, c: Int): Int = a + b + c

//  add(1)(2)(3)  ERRORY
  add(1, 2, 3)
  curry3(add)(1)(2)(3)

//  kuried add
  def add3(a: Int): (Int) => (Int) => Int =
    (b: Int) => (c: Int) => a + b + c

  add3(3)(2)(1)
//  add3(1, 2, 3)  ERRORY
  unCurry3(add3)(1, 2, 3)


  /*
  * Przekształć poniższą rekurencyjną definicję funkcji sumProd, która oblicza jednocześnie
sumę i iloczyn listy liczb całkowitych na równoważną definicję nierekurencyjną z
jednokrotnym użyciem funkcji bibliotecznej fold_left (Scala – foldLeft ), której argumentem jest
odpowiednia funkcja anonimowa (literał funkcyjny).
OCaml
Scala
let rec sumProd xs =
match xs with
h::t -> let (s,p)= sumProd t
in (h+s,h*p)
| []
-> (0,1);;
def sumProd(xs:List[Int]):(Int,Int) =
xs match {
case h::t => {val (s,p)=sumProd(t)
(h+s,h*p)
}
case Nil => (0,1)
  * */

  def sumProd(list: List[Int]): (Int, Int) =
    list.foldLeft(0, 1)((acc, x) => (acc._1 + x, acc._2 * x))

  /*5. Zdefiniuj funkcje sortowania
a) przez wstawianie z zachowaniem stabilności i złożoności O(n 2 )
insertionsort : (‘a->‘a->bool) -> ‘a list -> ‘a list .*/

  def insertionSort[A](isGreater: (A, A) => Boolean, list: List[A]): List[A] = {
    def insert(element: A, list: List[A]): List[A] = {
      list match {
        case Nil => List(element)
        case l@(h :: t) =>
          if (isGreater(element, h)) h :: insert(element, t)
          else element :: l
      }
    }

    list match {
      case Nil => Nil
      case h :: t => insert(h, insertionSort(isGreater, t))
    }
  }

  insertionSort((a: (Int, Int), b: (Int, Int)) => a._1 > b._1, List((4, 2), (3, 1), (3, 2), (5, 6)))

  println(insertionSort((a: Int, b: Int) => a > b, List(5, 3, 4, 2, 1)))

}
