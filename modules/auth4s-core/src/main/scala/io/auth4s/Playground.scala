package io.auth4s
import io.jsonwebtoken.Jwts

import java.util.UUID

object Playground extends App {

  val jwtClaims: JwtClaims[Nothing, Nothing] = JwtClaims.empty

  val pair: KeyPair = Jwts.SIG.RS512.keyPair.build
  val secretKey256: SecretKey = Jwts.SIG.HS256.key.build
  val secretKey384 = Jwts.SIG.HS384.key.build
  val secretKey512 = Jwts.SIG.HS512.key.build

  val config: JwtIssuerConfig = JwtIssuerConfig(
    algorithm = JwtIssueAlgorithm.Signature(
      SignatureAlgorithm.HS256(secretKey256)
    )
  )

  val s: Jwt[Nothing, Nothing] = JwtIssuer[IO](config).issue(jwtClaims).unsafeRunSync()

  println(s.token)
  println(secretKey256.getAlgorithm)
  val s1 = new String(secretKey256.getEncoded)
  println(s1.length)
  println(secretKey384.getAlgorithm)
  val s2 = new String(secretKey384.getEncoded)
  println(s2.length)
  println(secretKey512.getAlgorithm)
  val s3 = new String(secretKey512.getEncoded)
  println(s3.length)
  println(new String(Base64.getDecoder.decode(s.token.split('.').head)))
}
