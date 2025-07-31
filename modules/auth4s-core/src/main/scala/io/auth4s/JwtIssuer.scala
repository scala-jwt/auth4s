package io.auth4s

import cats.*
import cats.syntax.all.*
import io.auth4s.config.JwtIssuerConfig
import io.auth4s.encode.*
import io.auth4s.internal.syntax.all.*
import io.jsonwebtoken.{JwtBuilder, Jwts}

import java.security.Key
import scala.util.chaining.scalaUtilChainingOps

trait JwtIssuer[F[_]] {
  def issue[H, P](jwtClaims: JwtClaims[H, P])(using JwtClaimsEncoder[F, H, P]): F[Jwt[H, P]]
}

object JwtIssuer {

  def apply[F[_] : MonadThrow](config: JwtIssuerConfig): JwtIssuer[F] = new JwtIssuer[F] {
    val jwtBuilder: JwtBuilder = Jwts.builder()

    override def issue[H, P](jwtClaims: JwtClaims[H, P])(using
        JwtClaimsEncoder[F, H, P]
    ): F[Jwt[H, P]] =
      for {
        encoded <- jwtBuilder.encode(jwtClaims)
        issued  <- encoded.issue(config.algorithm)
      } yield issued.compact().pipe(Jwt(jwtClaims, _))
  }

  def noop[F[_] : Applicative]: JwtIssuer[F] = new JwtIssuer[F] {
    override def issue[H, P](jwtClaims: JwtClaims[H, P])(using JwtClaimsEncoder[F, H, P]): F[Jwt[H, P]] =
      Jwt.noop(jwtClaims).pure[F]
  }
}
