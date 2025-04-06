package io.auth4s

import cats.*
import io.jsonwebtoken.JwtBuilder

import scala.annotation.nowarn

trait JwtClaimsEncoder[F[_], H, P] {
  def encode(jwtClaims: JwtClaims[H, P], jwtBuilder: JwtBuilder): F[JwtBuilder]
}

object JwtClaimsEncoder {

  @nowarn
  given [F[_] : MonadThrow, H, P](using
      hje: HeaderEncoder[F, H],
      pje: PayloadEncoder[F, P],
      rje: PayloadEncoder[F, RegisteredClaims],
  ): JwtClaimsEncoder[F, H, P] =
    (jwtClaims, jwtBuilder) => ???
//      for {
//        headerClaims     <- jwtClaims.header.traverse(hje.encode)
//        payloadClaims    <- jwtClaims.payload.traverse(pje.encode)
//        registeredClaims <- rje.encode(jwtClaims.registered)
//      } yield jwtBuilder
//        .header()
//        .pipe(builder => headerClaims.fold(builder)(builder.add))
//        .and
//        .claims
//        .pipe(builder => payloadClaims.fold(builder)(builder.add))
//        .add(registeredClaims)
}
