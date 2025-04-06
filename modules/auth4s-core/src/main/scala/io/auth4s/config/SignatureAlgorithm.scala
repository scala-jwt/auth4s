package io.auth4s.config

import _root_.io.jsonwebtoken.security.{SignatureAlgorithm as JSignatureAlgorithm, *}
import io.jsonwebtoken.*

import java.security.{Key, PrivateKey, PublicKey}
import javax.crypto.SecretKey

sealed abstract class SignatureAlgorithm[PI <: Key, PU <: Key](
    val privateKey: PI,
    val publicKey: PU,
    val description: String,
) {
  def algorithm: SecureDigestAlgorithm[PI, PU]
}

object SignatureAlgorithm {

  final case class HS256(secretKey: SecretKey)
      extends SignatureAlgorithm[SecretKey, SecretKey](
        secretKey,
        secretKey,
        "HMAC using SHA-256",
      ) {
    def algorithm: MacAlgorithm = Jwts.SIG.HS256
  }

  final case class HS384(secretKey: SecretKey)
      extends SignatureAlgorithm[SecretKey, SecretKey](
        secretKey,
        secretKey,
        "HMAC using SHA-384",
      ) {
    override def algorithm: MacAlgorithm = Jwts.SIG.HS384
  }

  final case class HS512(secretKey: SecretKey)
      extends SignatureAlgorithm[SecretKey, SecretKey](
        secretKey,
        secretKey,
        "HMAC using SHA-512",
      ) {
    override def algorithm: MacAlgorithm = Jwts.SIG.HS512
  }

  final case class ES256(
      override val privateKey: PrivateKey,
      override val publicKey: PublicKey,
  ) extends SignatureAlgorithm[PrivateKey, PublicKey](
        privateKey,
        publicKey,
        "ECDSA using P-256 curve and SHA-256",
      ) {
    override def algorithm: JSignatureAlgorithm = Jwts.SIG.ES256
  }

  final case class ES384(
      override val privateKey: PrivateKey,
      override val publicKey: PublicKey,
  ) extends SignatureAlgorithm[PrivateKey, PublicKey](
        privateKey,
        publicKey,
        "ECDSA using P-384 curve and SHA-384",
      ) {
    override def algorithm: JSignatureAlgorithm = Jwts.SIG.ES384
  }

  final case class ES512(
      override val privateKey: PrivateKey,
      override val publicKey: PublicKey,
  ) extends SignatureAlgorithm[PrivateKey, PublicKey](
        privateKey,
        publicKey,
        "ECDSA using P-521 curve and SHA-512",
      ) {
    override def algorithm: JSignatureAlgorithm = Jwts.SIG.ES512
  }

  final case class RS256(
      override val privateKey: PrivateKey,
      override val publicKey: PublicKey,
  ) extends SignatureAlgorithm[PrivateKey, PublicKey](
        privateKey,
        publicKey,
        "RSASSA-PKCS1-v1_5 using SHA-256",
      ) {
    override def algorithm: JSignatureAlgorithm = Jwts.SIG.RS256
  }

  final case class RS384(
      override val privateKey: PrivateKey,
      override val publicKey: PublicKey,
  ) extends SignatureAlgorithm[PrivateKey, PublicKey](
        privateKey,
        publicKey,
        "RSASSA-PKCS1-v1_5 using SHA-384",
      ) {
    override def algorithm: JSignatureAlgorithm = Jwts.SIG.RS384
  }

  final case class RS512(
      override val privateKey: PrivateKey,
      override val publicKey: PublicKey,
  ) extends SignatureAlgorithm[PrivateKey, PublicKey](
        privateKey,
        publicKey,
        "RSASSA-PKCS1-v1_5 using SHA-512",
      ) {
    override def algorithm: JSignatureAlgorithm = Jwts.SIG.RS512
  }

  final case class PS256(
      override val privateKey: PrivateKey,
      override val publicKey: PublicKey,
  ) extends SignatureAlgorithm[PrivateKey, PublicKey](
        privateKey,
        publicKey,
        "RSASSA-PSS using SHA-256 and MGF1 with SHA-256",
      ) {
    override def algorithm: JSignatureAlgorithm = Jwts.SIG.PS256
  }

  final case class PS384(
      override val privateKey: PrivateKey,
      override val publicKey: PublicKey,
  ) extends SignatureAlgorithm[PrivateKey, PublicKey](
        privateKey,
        publicKey,
        "RSASSA-PSS using SHA-384 and MGF1 with SHA-384",
      ) {
    override def algorithm: JSignatureAlgorithm = Jwts.SIG.PS384
  }

  final case class PS512(
      override val privateKey: PrivateKey,
      override val publicKey: PublicKey,
  ) extends SignatureAlgorithm[PrivateKey, PublicKey](
        privateKey,
        publicKey,
        "RSASSA-PSS using SHA-512 and MGF1 with SHA-512",
      ) {
    override def algorithm: JSignatureAlgorithm = Jwts.SIG.PS512
  }

  final case class EdDSA(
      override val privateKey: PrivateKey,
      override val publicKey: PublicKey,
  ) extends SignatureAlgorithm[PrivateKey, PublicKey](
        privateKey,
        publicKey,
        "Edwards-curve Digital Signature Algorithm",
      ) {
    override def algorithm: JSignatureAlgorithm = Jwts.SIG.EdDSA
  }

  case object NONE extends SignatureAlgorithm[Key, Key](new DummyKey, new DummyKey, "No digital signature") {
    override def algorithm: SecureDigestAlgorithm[Key, Key] = Jwts.SIG.NONE
  }

  private class DummyKey extends Key {
    override def getAlgorithm: String    = "none"
    override def getFormat: String       = "none"
    override def getEncoded: Array[Byte] = Array.emptyByteArray
  }
}
