package io.auth4s

import cats.effect.IO
import cats.effect.unsafe.implicits.global
import io.jsonwebtoken.{JwtBuilder, Jwts}

import javax.crypto.SecretKey

object Playground extends App {

  val key: SecretKey = Jwts.SIG.HS256.key().build()

  val jwtClaims: JwtClaims[Nothing, Nothing] = JwtClaims.empty

  val s: Jwt[Nothing, Nothing] = JwtIssuer[IO].issue(jwtClaims).unsafeRunSync()

  println(s.token)
}
