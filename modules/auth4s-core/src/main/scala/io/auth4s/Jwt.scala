package io.auth4s

final case class Jwt[H, P](jwtClaims: JwtClaims[H, P], token: String)

object Jwt {
  def noop[H, P](jwtClaims: JwtClaims[H, P]): Jwt[H, P] = new Jwt(jwtClaims, "")
}
