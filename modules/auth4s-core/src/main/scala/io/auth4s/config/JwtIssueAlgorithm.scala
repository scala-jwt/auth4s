package io.auth4s.config

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.{MacAlgorithm, SecureDigestAlgorithm}
import _root_.io.jsonwebtoken.security.{SignatureAlgorithm as JSignatureAlgorithm, *}
import io.jsonwebtoken.*

import java.security.{Key, PrivateKey}
import javax.crypto.SecretKey

sealed abstract class JwtIssueAlgorithm[K](val key: K, val description: String) {}

object JwtIssueAlgorithm {

  sealed abstract class SignatureAlgorithm[K <: Key](key: K, description: String)
      extends JwtIssueAlgorithm[K](key, description) {
    def algorithm: SecureDigestAlgorithm[K, K]
  }

  final case class HS256(override val key: SecretKey) extends SignatureAlgorithm[SecretKey](key, "HMAC using SHA-256") {
    def algorithm: MacAlgorithm = Jwts.SIG.HS256
  }

  final case class HS384(override val key: SecretKey) extends SignatureAlgorithm[SecretKey](key, "HMAC using SHA-384") {
    override def algorithm: MacAlgorithm = Jwts.SIG.HS384
  }

  final case class HS512(override val key: SecretKey) extends SignatureAlgorithm[SecretKey](key, "HMAC using SHA-512") {
    override def algorithm: MacAlgorithm = Jwts.SIG.HS512
  }

  final case class ES256(override val key: PrivateKey) extends SignatureAlgorithm[PrivateKey](key, "ECDSA using P-256 curve and SHA-256") {
    override def algorithm: JSignatureAlgorithm = Jwts.SIG.ES256
  }

  final case class ES384(override val privateKey: PrivateKey) extends SignatureAlgorithm[PrivateKey, PublicKey](
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

//  final case class EncryptionAlgorithm(alg: EncryptionAlgorithm)                   extends JwtIssueAlgorithm

//  case object A128CBC_HS256 extends EncryptionAlgorithm("AES-128 CBC mode with HMAC-SHA-256 authentication")
//  case object A192CBC_HS384 extends EncryptionAlgorithm("AES-192 CBC mode with HMAC-SHA-384 authentication")
//  case object A256CBC_HS512 extends EncryptionAlgorithm("AES-256 CBC mode with HMAC-SHA-512 authentication")
//
//  case object A128GCM extends EncryptionAlgorithm("AES-128 in Galois/Counter Mode (GCM)")
//  case object A192GCM extends EncryptionAlgorithm("AES-192 in Galois/Counter Mode (GCM)")
//  case object A256GCM extends Encryp32tionAlgorithm("AES-256 in Galois/Counter Mode (GCM)")
//
  case object NONE extends SignatureAlgorithm[Key](new DummyKey, "None") {
    override def algorithm: SecureDigestAlgorithm[Key, Key] = Jwts.SIG.NONE
  }

  private class DummyKey extends Key {
    override def getAlgorithm: String    = "none"
    override def getFormat: String       = "none"
    override def getEncoded: Array[Byte] = Array.emptyByteArray
  }
}
