package com.paradygmaty.lab9

case class Rectangle(private val a: Double, private val b: Double) extends Shape {
  override def area(): Double = a * b

  override def circumference(): Double = 2 * a + 2 * b
}
