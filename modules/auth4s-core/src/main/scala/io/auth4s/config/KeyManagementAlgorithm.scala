package io.auth4s.config

sealed abstract class KeyManagementAlgorithm(val description: String)

object KeyManagementAlgorithm {
  case object RSA1_5             extends KeyManagementAlgorithm("RSAES-PKCS1-v1_5")
  case object RSA_OAEP           extends KeyManagementAlgorithm("RSAES OAEP using default parameters")
  case object RSA_OAEP_256       extends KeyManagementAlgorithm("RSAES OAEP using SHA-256 and MGF1 with SHA-256")
  case object A128KW             extends KeyManagementAlgorithm("AES Key Wrap with default initial value using 128-bit key")
  case object A192KW             extends KeyManagementAlgorithm("AES Key Wrap with default initial value using 192-bit key")
  case object A256KW             extends KeyManagementAlgorithm("AES Key Wrap with default initial value using 256-bit key")
  case object DIR                extends KeyManagementAlgorithm("Direct use of a shared symmetric key as the CEK")
  case object ECDH_ES
      extends KeyManagementAlgorithm("Elliptic Curve Diffie-Hellman Ephemeral Static key agreement using Concat KDF")
  case object ECDH_ES_A128KW     extends KeyManagementAlgorithm("ECDH-ES using Concat KDF and CEK wrapped with A128KW")
  case object ECDH_ES_A192KW     extends KeyManagementAlgorithm("ECDH-ES using Concat KDF and CEK wrapped with A192KW")
  case object ECDH_ES_A256KW     extends KeyManagementAlgorithm("ECDH-ES using Concat KDF and CEK wrapped with A256KW")
  case object A128GCMKW          extends KeyManagementAlgorithm("Key wrapping with AES GCM using 128-bit key")
  case object A192GCMKW          extends KeyManagementAlgorithm("Key wrapping with AES GCM using 192-bit key")
  case object A256GCMKW          extends KeyManagementAlgorithm("Key wrapping with AES GCM using 256-bit key")
  case object PBES2_HS256_A128KW extends KeyManagementAlgorithm("PBES2 with HMAC SHA-256 and A128KW wrapping")
  case object PBES2_HS384_A192KW extends KeyManagementAlgorithm("PBES2 with HMAC SHA-384 and A192KW wrapping")
  case object PBES2_HS512_A256KW extends KeyManagementAlgorithm("PBES2 with HMAC SHA-512 and A256KW wrapping")
  case object NONE               extends KeyManagementAlgorithm("No key management algorithm")
}
