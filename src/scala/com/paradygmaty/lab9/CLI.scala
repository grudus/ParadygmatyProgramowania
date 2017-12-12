package com.paradygmaty.lab9

import scala.io.StdIn.readLine

object CLI {

  def main(args: Array[String]): Unit = {

    val input = readLine("Podaj figure (KOLO|PROSTOKAT|KWADRAT|TROJKAT): ")

    val figure: Shape = input.toUpperCase match {
      case "KOLO" => Circle(readLine("Podaj promien: ").toDouble)
      case "PROSTOKAT" =>
        val a :: b :: _ = readLine("Podaj a i b: ").split("\\s+").map(_.toDouble).toList
        Rectangle(a, b)
      case "KWADRTAT" => Square(readLine("Podaj bok: ").toDouble)
      case "TROJKAT" =>
        val a :: b :: c :: _ = readLine("Podaj boki a, b i c: ").split("\\s+").map(_.toDouble).toList
        Triangle(a, b, c)
      case _ => throw new IllegalArgumentException("Nie mozna znalezc figury")
    }

    println(figure)
    println(s"Pole: ${figure.area()}")
    println(s"Obwod: ${figure.circumference()}")
  }
}