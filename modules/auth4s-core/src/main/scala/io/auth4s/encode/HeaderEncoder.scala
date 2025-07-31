package io.auth4s.encode

import cats.syntax.all.*
import cats.{Applicative, ApplicativeThrow}
import io.jsonwebtoken.*

trait HeaderEncoder[F[_], H] {
  def encode(header: H): F[Header]
}

object HeaderEncoder {

  given [F[_] : Applicative]: HeaderEncoder[F, Nothing] = (_: Nothing) => Jwts.header().build().pure[F]

  def apply[F[_] : ApplicativeThrow, H](build: (H, Jwts.HeaderBuilder) => Header): HeaderEncoder[F, H] =
    (header: H) => ApplicativeThrow[F].catchNonFatal(build(header, Jwts.header()))
}
