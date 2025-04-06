package io.auth4s

import cats.ApplicativeThrow
import io.jsonwebtoken.ClaimsBuilder

import java.time.Instant
import java.util.Date
import scala.jdk.CollectionConverters.SeqHasAsJava
import scala.util.chaining.scalaUtilChainingOps

final case class RegisteredClaims(
    iss: Option[String] = None,
    sub: Option[String] = None,
    aud: Seq[String] = Seq.empty,
    exp: Option[Instant] = None,
    nbf: Option[Instant] = None,
    iat: Option[Instant] = None,
    jti: Option[String] = None,
)

object RegisteredClaims {

  given claimsEncoder[F[_] : ApplicativeThrow]: PayloadEncoder[F, RegisteredClaims] =
    PayloadEncoder.apply[F, RegisteredClaims] { (claims: RegisteredClaims, builder: ClaimsBuilder) =>
      builder
        .pipe(builder => claims.iss.fold(builder)(builder.issuer))
        .pipe(builder => claims.sub.fold(builder)(builder.subject))
        .pipe(builder => builder.audience().add(claims.aud.asJava).and())
        .pipe(builder => claims.exp.fold(builder)(i => builder.expiration(Date.from(i))))
        .pipe(builder => claims.nbf.fold(builder)(i => builder.notBefore(Date.from(i))))
        .pipe(builder => claims.iat.fold(builder)(i => builder.issuedAt(Date.from(i))))
        .pipe(builder => claims.jti.fold(builder)(builder.id))
        .build()
    }

  def empty: RegisteredClaims = RegisteredClaims()
}
