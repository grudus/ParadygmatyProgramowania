package com.paradygmaty.lab9

import java.lang.Math.sqrt

case class Triangle(private val a: Double, private val b: Double, private val c: Double) extends Shape {

  if (a + b <= c || a + c <= b || b + c <= a)
    throw new IllegalArgumentException("Cannot create valid triangle")


  override def area(): Double = {
    val p = (a + b + c) / 2
    sqrt(p * (p - a) * (p - b) * (p - c))
  }

  override def circumference(): Double = a + b + c
}
