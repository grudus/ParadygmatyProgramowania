package com.paradygmaty

object List4_lab {


  /*Za pomocą ADT i pattern maching’u napisz prosty kalkulator, który obsługuje dwie
funkcje: dodawania (a+b) i negacji (-n).*/

  sealed trait Expression

  case class Const(value: Double) extends Expression

  case class Var(name: String) extends Expression

  case class Sum(a: Expression, b: Expression) extends Expression

  case class Negative(a: Expression) extends Expression


  def eval(exp: Expression, env: Map[String, Double]): Double = {
    exp match {
      case Const(v) => v
      case Var(name) => env(name)
      case Sum(a, b) => eval(a, env) + eval(b, env)
      case Negative(a) => - eval(a, env)
    }
  }

  val env = Map("a" -> 5.0, "b" -> 7.0, "c" -> -2.0)

  println(eval(Sum(Sum(Var("a"), Negative(Var("b"))), Var("c")), env))


}
