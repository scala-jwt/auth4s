package io.auth4s

final case class JwtClaims[H, P](header: Option[H], payload: Option[P], registered: RegisteredClaims)

object JwtClaims {

  type JwtClaimsR    = JwtClaims[Nothing, Nothing]
  type JwtClaimsH[H] = JwtClaims[H, Nothing]
  type JwtClaimsP[P] = JwtClaims[Nothing, P]

  lazy val empty: JwtClaimsR =
    JwtClaims(None, None, RegisteredClaims.empty)

//      jwtBuilder
//      jwtClaims.header
//        .fold(jwtBuilder)(header => hje.encode(header, jwtBuilder))
//        .pipe(jwtBuilder => jwtClaims.payload.fold(jwtBuilder)(payload => pje.encode(payload, jwtBuilder)))
//        .pipe(jwtBuilder => rje.encode(jwtClaims.registered, jwtBuilder))
}
