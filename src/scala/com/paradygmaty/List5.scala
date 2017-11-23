package com.paradygmaty

object List5 {
  /*Zdefiniuj funkcję lrepeat : int -> 'a llist -> 'a llist, która dla danej dodatniej liczby całkowitej k i listy
  leniwej (strumienia w Scali) [x 0 , x 1 , x 2 , x 3 , ... ] zwraca listę leniwą (strumień w Scali), w której
  każdy element jest powtórzony k razy, np.
  lrepeat 3 [x 0, x 1 , x 2 , x 3 , ... ] => [x 0 , x 0 , x 0 , x 1 , x 1 , x 1 , x 2 , x 2 , x 2 , x 3 , x 3 , x 3 , ... ]
  Uwaga. Dla zwiększenia czytelności zastosowano tu notację dla zwykłych list.*/

  def repeat[A](k: Int, xs: Stream[A]): Stream[A] = {
    def wew(n: Int, xs: Stream[A]): Stream[A] =
      (n, xs) match {
        case (_, Stream.Empty) => Stream.Empty
        case (0, _ #:: t) => wew(k, t)
        case (i, d@h #:: _) => h #:: wew(i - 1, d)
      }

    wew(k, xs)
  }

  println(repeat(3, Stream(1, 2, 3)).toList)


  /*Zdefiniuj (w inny sposób, niż na wykładzie) leniwą listę liczb Fibonacciego
 lfib : Stream[Int].*/

  val lfib: Stream[BigInt] = {
    def fib(current: BigInt, next: BigInt): Stream[BigInt] =
      current #:: fib(next, current + next)

    fib(BigInt.int2bigInt(0), BigInt.int2bigInt(1))
  }



  /*3. (OCaml i Scala) Polimorficzne leniwe drzewa binarne można zdefiniować następująco:
OCaml:
type 'a lBT = LEmpty | LNode of 'a * (unit ->'a lBT) * (unit -> 'a lBT);;
Scala:
sealed trait lBT[+A]
case object LEmpty extends lBT[Nothing]
case class LNode[+A](elem:A, left:()=>lBT[A], right:()=>lBT[A]) extends lBT[A]
a) Napisz funkcję lTree , która dla zadanej liczby naturalnej n konstruuje nieskończone leniwe
drzewo binarne z korzeniem o wartości n i z dwoma poddrzewami lTree (2*n) oraz lTree( 2*n+1).
To drzewo jest przydatne do testowania funkcji z następnego podpunktu.
b) Napisz funkcję, tworzącą leniwą listę w OCamlu (strumień w Scali), zawierającą wszystkie
wartości węzłów leniwego drzewa binarnego.
Wskazówka: zastosuj obejście drzewa wszerz, reprezentując kolejkę jako zwykłą listę.*/


  sealed trait lBT[+A]

  case object LEmpty extends lBT[Nothing]

  case class LNode[+A](elem: A, left: () => lBT[A], right: () => lBT[A]) extends lBT[A]

  def lTree(n: Int): lBT[Int] = LNode(n, () => lTree(2 * n), () => lTree(2 * n + 1))

  def brBT[A](tree: lBT[A]): Stream[A] = {
    def bsf(queue: List[lBT[A]]): Stream[A] =
      queue match {
        case Nil => Stream.empty[A]
        case LEmpty :: t => bsf(t)
        case LNode(v, l, r) :: t => v #:: bsf(t ++ List(l(), r()))
      }

    bsf(List(tree))
  }

  println(brBT(lTree(10)).take(15).toList)


}
