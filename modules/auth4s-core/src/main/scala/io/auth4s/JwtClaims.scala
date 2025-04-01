package io.auth4s

import scala.util.chaining.scalaUtilChainingOps

final case class JwtClaims[H, P](header: Option[H], payload: Option[P], registered: RegisteredClaims)

object JwtClaims {

  lazy val empty: JwtClaims[Nothing, Nothing] =
    JwtClaims(None, None, RegisteredClaims.empty)

  given [H, P](using
      hje: JwtClaimsEncoder[H],
      pje: JwtClaimsEncoder[P],
      rje: JwtClaimsEncoder[RegisteredClaims],
  ): JwtClaimsEncoder[JwtClaims[H, P]] =
    (jwtClaims, jwtBuilder) =>
      jwtClaims.header
        .fold(jwtBuilder)(header => hje.encode(header, jwtBuilder))
        .pipe(jwtBuilder => jwtClaims.payload.fold(jwtBuilder)(payload => pje.encode(payload, jwtBuilder)))
        .pipe(jwtBuilder => rje.encode(jwtClaims.registered, jwtBuilder))
}
