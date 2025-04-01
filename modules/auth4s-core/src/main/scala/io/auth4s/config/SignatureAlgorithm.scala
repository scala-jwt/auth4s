package io.auth4s.config

sealed abstract class SignatureAlgorithm(val description: String)

object SignatureAlgorithm {
  case object HHS256 extends SignatureAlgorithm("HMAC using SHA-256")
  case object HHS384 extends SignatureAlgorithm("HMAC using SHA-384")
  case object HHS512 extends SignatureAlgorithm("HMAC using SHA-512")
  case object ES256  extends SignatureAlgorithm("ECDSA using P-256 curve and SHA-256")
  case object ES384  extends SignatureAlgorithm("ECDSA using P-384 curve and SHA-384")
  case object ES512  extends SignatureAlgorithm("ECDSA using P-521 curve and SHA-512")
  case object RS256  extends SignatureAlgorithm("RSASSA-PKCS1-v1_5 using SHA-256")
  case object RS384  extends SignatureAlgorithm("RSASSA-PKCS1-v1_5 using SHA-384")
  case object RS512  extends SignatureAlgorithm("RSASSA-PKCS1-v1_5 using SHA-512")
  case object PS256  extends SignatureAlgorithm("RSASSA-PSS using SHA-256 and MGF1 with SHA-256")
  case object PS384  extends SignatureAlgorithm("RSASSA-PSS using SHA-384 and MGF1 with SHA-384")
  case object PS512  extends SignatureAlgorithm("RSASSA-PSS using SHA-512 and MGF1 with SHA-512")
  case object EdDSA  extends SignatureAlgorithm("Edwards-curve Digital Signature Algorithm")
  case object NONE   extends SignatureAlgorithm("No digital signature")
}
