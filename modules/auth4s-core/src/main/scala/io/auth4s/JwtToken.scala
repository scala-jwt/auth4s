package io.auth4s

sealed abstract class JwtToken(val token: String)

object JwtToken {
  lazy val empty: Token = JwtToken.Token("")

  final case class Token(override val token: String)   extends JwtToken(token)
  final case class TokenH(override val token: String)  extends JwtToken(token)
  final case class TokenP(override val token: String)  extends JwtToken(token)
  final case class TokenHP(override val token: String) extends JwtToken(token)
}
