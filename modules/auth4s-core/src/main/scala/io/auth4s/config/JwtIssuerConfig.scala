package io.auth4s.config

import java.security.Key

final case class JwtIssuerConfig(
    algorithm: Option[JwtIssueAlgorithm] = None
)
