package com.paradygmaty

import scala.annotation.tailrec

object Lab4 {

  sealed trait Tree[+A]

  case class Node[+A](value: A, left: Tree[A], right: Tree[A]) extends Tree[A]

  case object Empty extends Tree[Nothing]

  val tree = Node(12, Node(55, Node(-12, Node(0, Empty, Empty), Empty), Node(11, Empty, Empty)), Empty)

  def countNodes[A](tree: Tree[A]): Int =
    tree match {
      case Empty => 0
      case Node(_, l, r) => 1 + countNodes(l) + countNodes(r)
    }


  println(countNodes(tree))

  def preOrder[A](tree: Tree[A]): List[A] =
    tree match {
      case Empty => Nil
      case Node(v, l, r) => v :: preOrder(l) ++ preOrder(r)
    }


  def inOrder[A](tree: Tree[A]): List[A] =
    tree match {
      case Empty => Nil
      case Node(v, l, r) => inOrder(l) ++ (v :: inOrder(r))
    }


  def postOrder[A](tree: Tree[A]): List[A] =
    tree match {
      case Empty => Nil
      case Node(v, l, r) => postOrder(l) ++ postOrder(r) ++ List(v)
    }

  println(preOrder(tree))
  println(inOrder(tree))
  println(postOrder(tree))


  def createBinarySearchTree(list: List[Int]): Tree[Int] = {

    def insertToBst(k: Int, tree: Tree[Int]): Tree[Int] = {
      tree match {
        case Node(r, lt, rt) =>
          if (k < r) Node(r, insertToBst(k, lt), rt)
          else if (k > r) Node(r, lt, insertToBst(k, rt))
          else throw new Exception(s"Duplicated keys $k")
        case Empty => Node(k, Empty, Empty)
      }
    }

    list match {
      case h :: t => insertToBst(h, createBinarySearchTree(t))
      case Nil => Empty
    }
  }

  println(createBinarySearchTree(List(6, 4, 9, 2, 5)))


  /*Dla drzew binarnych, zdefiniowanych na wykładzie, napisz funkcję breadthBT : 'a bt -> 'a list
obchodzącą drzewo wszerz i zwracającą zawartość wszystkich węzłów drzewa w postaci listy.*/


  val tt = Node(1, Node(2, Node(4, Empty, Empty), Empty), Node(3, Node(5, Empty, Node(6, Empty, Empty)), Empty))


  def breadthBT[A](tree: Tree[A]): List[A] = {
    def breadth(queue: List[Tree[A]]): List[A] = {
      queue match {
        case Nil => Nil
        case Empty :: t => breadth(t)
        case Node(v, l, r) :: t => v :: breadth(t ++ List(l, r))
      }
    }

    breadth(List(tree))
  }

  println(breadthBT(tt))


  /*W regularnym drzewie binarnym każdy z węzłów jest bądź liściem, bądź ma stopień dwa (patrz
  Cormen i in. §5.5.3). Zauważ, że drzewa ’a bt są drzewami regularnymi – traktujemy konstruktor
  Empty jako liść.
  Długość ścieżki wewnętrznej i regularnego drzewa binarnego jest sumą, po wszystkich węzłach
  wewnętrznych drzewa, głębokości każdego węzła. Długość ścieżki zewnętrznej e jest sumą, po
  wszystkich liściach drzewa, głębokości każdego liścia. Głębokość węzła definiujemy jako liczbę
  krawędzi od korzenia do tego węzła.
  Napisz dwie funkcje, obliczające odpowiednio
  a) długość ścieżki wewnętrznej
  b) długość ścieżki zewnętrznej
  zadanego regularnego drzewa binarnego*/


  def wewn[A](tree: Tree[A]): Int = {
    def geti(tree: Tree[A], depth: Int): Int =
      (tree, depth) match {
        case (Empty, _) => 0
        case (Node(_, l, r), currentDepth) => currentDepth + geti(l, currentDepth + 1) + geti(r, currentDepth + 1)
      }

    geti(tree, 0)
  }


  def zewn[A](tree: Tree[A]): Int = {
    def gete(tree: Tree[A], depth: Int): Int =
      (tree, depth) match {
        case (Empty, currentDepth) => currentDepth
        case (Node(_, l, r), currentDepth) => 0 + gete(l, currentDepth + 1) + gete(r, currentDepth + 1)
      }

    gete(tree, 0)
  }

  println(wewn(tt))
  println(zewn(tt))


  /**/

  sealed trait Graphs[+A]

  case class Graph[A](succ: A => List[A]) extends Graphs[A]



  val g = Graph((i: Int) => i match {
    case 0 => List(3)
    case 1 => List(0, 2, 4)
    case 2 => List(1)
    case 3 => Nil
    case 4 => List(0, 2)
    case n => throw new Exception(s"Graph g: node $n doesn't exist")
  })

  def breadthSearch[A](graph: Graph[A])(startNode: A): List[A] = {
    def search(visited: List[A])(toVisit: List[A]): List[A] =
      toVisit match {
        case Nil => Nil
        case h :: t if visited contains h => search(visited)(t)
        case h :: t => h :: search(h :: visited)(t ++ (graph succ h))
      }

    search(Nil)(List(startNode))
  }
  println(breadthSearch(g)(4))

  /*Dla grafów, zdefiniowanych na wykładzie, napisz funkcję depthSearch : 'a graph -> 'a -> 'a list,
obchodzącą graf w głąb zaczynając od zadanego wierzchołka i zwracającą zawartość
zwizytowanych węzłów w postaci listy. Np. dla grafu g z wykładu:
depthSearch g 4 => [4; 0; 3; 2; 1]*/

  def depthSearch[A](graph: Graph[A])(startNode: A): List[A] = {
    def search(visited: List[A])(toVisit: List[A]): List[A] =
      toVisit match {
        case Nil => Nil
        case h::t =>
          if (visited contains h) search(visited)(t)
          else h :: search(h::visited)((graph succ h) ++ t)
      }
    search(Nil)(List(startNode))
  }

  println(depthSearch(g)(4))



  val graph2 = Graph((s: String) => s match {
    case "v0" => List("v1", "v5")
    case "v1" => Nil
    case "v2" => List("v3", "v1")
    case "v3" => List("v4")
    case "v4" => List("v1", "v5")
    case "v5" => List("v2", "v1")
    case _ => throw new Exception("Ni ma")
  })

  println(depthSearch(graph2)("v0"))

}
