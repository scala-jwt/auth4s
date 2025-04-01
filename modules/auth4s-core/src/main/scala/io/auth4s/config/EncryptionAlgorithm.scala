package io.auth4s.config

sealed abstract class EncryptionAlgorithm(val description: String)

object EncryptionAlgorithm {
  case object A128CBC_HS256 extends EncryptionAlgorithm("AES-128 CBC mode with HMAC-SHA-256 authentication")
  case object A192CBC_HS384 extends EncryptionAlgorithm("AES-192 CBC mode with HMAC-SHA-384 authentication")
  case object A256CBC_HS512 extends EncryptionAlgorithm("AES-256 CBC mode with HMAC-SHA-512 authentication")
  case object A128GCM       extends EncryptionAlgorithm("AES-128 in Galois/Counter Mode (GCM)")
  case object A192GCM       extends EncryptionAlgorithm("AES-192 in Galois/Counter Mode (GCM)")
  case object A256GCM       extends EncryptionAlgorithm("AES-256 in Galois/Counter Mode (GCM)")
  case object NONE          extends EncryptionAlgorithm("No encryption")
}
