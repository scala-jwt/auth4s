package io.auth4s

import cats.*
import cats.syntax.all.*
import io.jsonwebtoken.{JwtBuilder, Jwts}

trait JwtIssuer[F[_]] {
  def issue[H, P](jwtClaims: JwtClaims[H, P])(using JwtClaimsEncoder[JwtClaims[H, P]]): F[Jwt[H, P]]
}

object JwtIssuer {

  def apply[F[_] : MonadThrow]: JwtIssuer[F] = new JwtIssuer[F] {
    val builder: JwtBuilder = Jwts.builder()

    override def issue[H, P](jwtClaims: JwtClaims[H, P])(using
        encoder: JwtClaimsEncoder[JwtClaims[H, P]]
    ): F[Jwt[H, P]] =
      encoder.encode(jwtClaims, builder).compact().pure[F].map(Jwt(jwtClaims, _))
  }

  def noop[F[_] : Applicative]: JwtIssuer[F] = new JwtIssuer[F] {
    override def issue[H, P](jwtClaims: JwtClaims[H, P])(using JwtClaimsEncoder[JwtClaims[H, P]]): F[Jwt[H, P]] =
      Jwt.noop(jwtClaims).pure[F]
  }
}
