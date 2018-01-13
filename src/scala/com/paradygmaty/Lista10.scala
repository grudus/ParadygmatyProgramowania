//package com.paradygmaty
//
//class Zwierze(val id:Int = 0) {
//  override def toString:String = "_Zwierzê" + id
//}
//
//class Ssak(id:Int = 0) extends Zwierze(id) {
//  override def toString = "_Ssak" + id
//}
//
//class Malpa(id:Int = 0) extends Ssak(id) {
//  override def toString = "_Ma³pa" + id
//}
//
//class Tygrys(id:Int = 0) extends Ssak(id) {
//  override def toString = "_Tygrys" + id
//}
//
//
//class GenericCellImm1[T] (val x: T) {
//}
//
//class GenericCellImm2[+T] (val x: T) {
//}
//
//class GenericCellMut3[T] (var x: T) {
//}
//
//
//class EmptyException(message:String = "") extends RuntimeException(message)
//
//class Queue[+T] private (val l1:List[T], val l2:List[T]) {
//
//  def this() = this(Nil, Nil)
//
//  def enqueue[S >: T](elem: S): Queue[S] = {
//    (l1, l2) match {
//      case (Nil, Nil) => new Queue(List(elem), Nil)
//      case (l1, l2) => new Queue(l1, elem :: l2)
//    }
//  }
//
//  def dequeue(): Queue[T] = {
//    (l1, l2) match {
//      case (Nil, Nil) => this
//      case (List(_), l) => new Queue(l.reverse, Nil)
//      case (_ :: t, l) => new Queue(t, l)
//    }
//  }
//
//  def first(): T =
//    l1 match {
//      case Nil => throw new EmptyException("Kolejka jest pusta!")
//      case h :: _ => h
//    }
//
//  def isEmpty: Boolean = l1 == Nil
//
//  //override def toString(): String = "{" + l1.mkString(",") + "}|{" + l2.mkString(",") + "}"
//}
//
///*
//class GenericCellMut[-T] (private[this] var x: T) {
//  def x_=(x:T) = this.x = x
//}
//
//class GenericCellMut[-T] (var x: T) {
//}
//
//
//abstract class Sequence[+A] {
//  def append(x: Sequence[A]): Sequence[A]
//}*/
//
//abstract class Sequence[+A](){
//  def append[B >: A](x: Sequence[B]): Sequence[B]
//}
//
//abstract class Sequence2[+A](var lista:List[Zwierze]){
//  def append[B >: A](x: Sequence2[B]): Sequence2[B]
//}
//
//class SeqI[+A] extends Sequence[A] {
//  override def append[B >: A](x: Sequence[B]) = {
//    x
//  }
//}
//
//class ZwierzeSequence extends Sequence[Zwierze] {
//  override def append[B >: Zwierze](x: Sequence[B]) = {
//    x
//  }
//}
//
//class SsakSequence extends Sequence[Ssak] {
//  override def  append[B >: Ssak](x: Sequence[B]) = {
//    x
//  }
//}
//
//class MalpaSequence extends Sequence[Malpa] {
//  override def  append[B >: Malpa](x: Sequence[B]) = {
//    x
//  }
//}
////
////class SeqImpl[+A](var lista:List[A]) extends Sequence[A]{
//////  override def append[B >: A](x: SeqImpl[B]): SeqImpl[B] = {
////////    lista = lista ++ x.lista
//////    x
////  }
////}
//
//class ZSeq(lista2:List[Zwierze]) extends Sequence2[Zwierze](lista2){
//  override def append[B](x: Sequence2[B]): Sequence2[B] = {
//    lista = lista ++ x.lista
//    x
//  }
//}
//
//class Stack[+A] {
//  def push[B >: A](elem: B): Stack[B] = new Stack[A] {
//    override def top: B = elem
//    override def pop: Stack[B] = Stack.this
//    override def toString() = elem.toString() + " " + Stack.this.toString()
//  }
//  def top: A = error("no element on stack")
//  def pop: Stack[A] = error("no element on stack")
//  override def toString() = "Empty" + super.toString()
//}
//
//
//import scala.collection.mutable.Seq
//import scala.collection.mutable.MutableList
//
//object Lista10 {
//  def copy[T](dest: Seq[T], src: Seq[T]) = {
//    var i = 0
//    src.foreach { elem =>
//      dest.update(i, elem)
//      i += 1
//    }
//  }
//
//  def main(args: Array[String]): Unit = {
//    val zwierze = new Zwierze
//    val ssak = new Ssak
//    val malpa = new Malpa
//    val tygrys = new Tygrys
//
//    var o1:GenericCellImm2[Malpa] = new GenericCellImm2(new Malpa)
//    var o2:GenericCellImm2[Ssak] = o1
//    o1.x
//    o2 = o1
//    o2.x
//
//    val s1 = new MutableList[Zwierze]
//
//    val queue0 = new Queue[Int]
//
//    val queue1 = queue0.enqueue(1)
//    val queue2 = queue1.enqueue(2)
//    val queue3 = queue2.enqueue(3)
//    val queue4 = queue3.enqueue(4)
//    queue4.first
//    val queue5 = queue4.dequeue()
//    queue5.first
//    val queue6 = queue5.enqueue(5)
//    val queue7 = queue6.dequeue()
//    queue7.first
//    val queue8 = queue7.enqueue(6)
//    val queue9 = queue8.dequeue()
//    queue9.first
//    val queue10 = queue9.dequeue()
//    queue10.first
//    val queue11 = queue10.dequeue()
//    queue11.first
//    val queue12 = queue11.dequeue()
//    queue12.first
//    val queue13 = queue12.dequeue()
//
//
//    var si1:SeqI[Malpa] = new SeqI[Malpa]
//
//    var sm:Sequence[Malpa] = new MalpaSequence
//    var sz:Sequence[Zwierze] = sm
//    sz.append(new MalpaSequence)
//    sz.append(new SsakSequence)
//    sz.append(new ZwierzeSequence)
//
//
//    sm.append(new MalpaSequence)
//    sm.append(new SsakSequence)
//    sm.append(new ZwierzeSequence)
//
//    var ss:Sequence[Ssak] = new SsakSequence
//
//    ss.append(new ZwierzeSequence)
//    ss.append(new MalpaSequence)
//
//    var stackM:Stack[Malpa] = new Stack[Malpa]
//    stackM = stackM.push(new Malpa)
//
//    var stackZ:Stack[Zwierze] = stackM
//    var stackS:Stack[Ssak] = stackM
//
//    stackZ = stackZ.push(new Zwierze)
//
//
//    stackS = new Stack[Ssak]
//
//    stackS = stackS.push(new Ssak)
//    stackS = stackS.push(new Malpa)
////    stackS = stackS.push(new Zwierze)
//
//
//  }
//
//}
