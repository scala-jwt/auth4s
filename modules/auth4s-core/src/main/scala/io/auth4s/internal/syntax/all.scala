package io.auth4s.internal.syntax

import cats.*
import cats.syntax.all.*
import io.auth4s.*
import io.auth4s.config.JwtIssueAlgorithm
import io.auth4s.encode.*
import io.jsonwebtoken.JwtBuilder

import java.security.Key

private[auth4s] object all {

  extension (jwtBuilder: JwtBuilder) {
    def encode[F[_], H, P](jwtClaims: JwtClaims[H, P])(using encoder: JwtClaimsEncoder[F, H, P]): F[JwtBuilder] =
      encoder.encode(jwtClaims, jwtBuilder)
  }

  extension (jwtBuilder: JwtBuilder) {
    def issue[F[_] : MonadThrow](
        jwtIssueAlgorithm: Option[JwtIssueAlgorithm]
    ): F[JwtBuilder] =
      jwtIssueAlgorithm match {
        case Some(signAlgorithm: JwtIssueAlgorithm.Signature) =>
          MonadThrow[F].catchNonFatal(jwtBuilder.signWith(signAlgorithm.alg.privateKey, signAlgorithm.alg.algorithm))
        case Some(JwtIssueAlgorithm.Encryption(_))            =>
          MonadThrow[F].catchNonFatal(jwtBuilder.encryptWith())
        case None                                             => jwtBuilder.pure[F]
      }
  }
}
