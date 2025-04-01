package io.auth4s

import io.jsonwebtoken.JwtBuilder

trait JwtClaimsEncoder[T] {
  def encode(claims: T, builder: JwtBuilder): JwtBuilder
}

object JwtClaimsEncoder {

  given JwtClaimsEncoder[Nothing] = (_: Nothing, builder: JwtBuilder) => builder
}
