package io.auth4s.internal.syntax

import io.auth4s.{JwtClaims, JwtClaimsEncoder}
import io.jsonwebtoken.JwtBuilder

private[auth4s] object all {

  extension (jwtBuilder: JwtBuilder) {
    def encode[F[_], H, P](jwtClaims: JwtClaims[H, P])(using encoder: JwtClaimsEncoder[F, H, P]): F[JwtBuilder] =
      encoder.encode(jwtClaims, jwtBuilder)
  }

//  extension (jwtBuilder: JwtBuilder) {
//    def issue[F[_] : MonadThrow](
//        jwtIssueAlgorithm: JwtIssueAlgorithm
//    ): F[JwtBuilder] =
//      jwtIssueAlgorithm match {
//        case signAlgorithm: JwtIssueAlgorithm.Signature[Key, Key] =>
//          MonadThrow[F].catchNonFatal(jwtBuilder.signWith(signAlgorithm.alg.privateKey, signAlgorithm.alg.algorithm))
//        case JwtIssueAlgorithm.Encryption(_)                      => ???
//      }
//  }
}
