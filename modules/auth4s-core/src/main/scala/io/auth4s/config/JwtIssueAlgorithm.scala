package io.auth4s.config

import java.security.Key

sealed trait JwtIssueAlgorithm

object JwtIssueAlgorithm {

  final case class Signature[PI <: Key, PU <: Key](alg: SignatureAlgorithm[PI, PU]) extends JwtIssueAlgorithm
  final case class Encryption(alg: EncryptionAlgorithm)                             extends JwtIssueAlgorithm
}
