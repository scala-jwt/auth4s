package io.auth4s

object MacrosUsage extends App {

  enum JwtToken {
    case Simple
    case Complex
  }

  sealed trait JwtToken1

  object JwtToken1 {
    case class Simple() extends JwtToken1
    case class Complex() extends JwtToken1
  }

  val d = Auth4sEnum.derived[JwtToken]
//  val d1 = Auth4sEnum.derivied[JwtToken1]

  println(d)
}
