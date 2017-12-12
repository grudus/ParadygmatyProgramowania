package com.paradygmaty.lab9

case class Circle(private val radius: Double) extends Shape {

  override def area(): Double = Math.PI * radius * radius

  override def circumference(): Double = 2 * Math.PI * radius
}
