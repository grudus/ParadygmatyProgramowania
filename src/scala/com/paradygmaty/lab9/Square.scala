package com.paradygmaty.lab9

case class Square(private val a: Double) extends Shape {

  override def area(): Double = a * a

  override def circumference(): Double = 4 * a
}
