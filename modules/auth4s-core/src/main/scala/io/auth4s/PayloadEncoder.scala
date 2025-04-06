package io.auth4s

import cats.*
import cats.syntax.all.*
import io.jsonwebtoken.*

trait PayloadEncoder[F[_], P] {
  def encode(payload: P): F[Claims]
}

object PayloadEncoder {

  given [F[_] : Applicative]: PayloadEncoder[F, Nothing] = (_: Nothing) => Jwts.claims().build().pure[F]

  def apply[F[_] : ApplicativeThrow, P](build: (P, ClaimsBuilder) => Claims): PayloadEncoder[F, P] =
    (payload: P) => ApplicativeThrow[F].catchNonFatal(build(payload, Jwts.claims()))
}
