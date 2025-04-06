package io.auth4s

import org.scalacheck.*
import org.scalacheck.Arbitrary.arbitrary

import java.time.Instant

trait Arbitraries {

  given Arbitrary[String] = Arbitrary(Gen.alphaStr)

//  given Arbitrary[JwtToken] = Arbitrary {
//    for {
//      header    <- arbitrary[String]
//      payload   <- arbitrary[String]
//      signature <- arbitrary[String]
//    } yield JwtToken(s"$header.$payload.$signature")
//  }

  given Arbitrary[RegisteredClaims] = Arbitrary {
    for {
      issuer     <- Gen.option(arbitrary[String])
      subject    <- Gen.option(arbitrary[String])
      audience   <- Gen.listOf(arbitrary[String])
      expiration <- Gen.option(arbitrary[Instant])
      notBefore  <- Gen.option(arbitrary[Instant])
      issuedAt   <- Gen.option(arbitrary[Instant])
      jwtId      <- Gen.option(arbitrary[String])
    } yield RegisteredClaims(
      issuer,
      subject,
      audience,
      expiration,
      notBefore,
      issuedAt,
      jwtId,
    )
  }

  given jwtClaimsArb[H : Arbitrary, P : Arbitrary]: Arbitrary[JwtClaims[H, P]] =
    Arbitrary {
      for {
        header     <- Gen.option(arbitrary[H])
        payload    <- Gen.option(arbitrary[P])
        registered <- arbitrary[RegisteredClaims]
      } yield JwtClaims(header, payload, registered)
    }

//  given Arbitrary[EncryptionAlgorithm] = Arbitrary {
//    for {
//      header    <- arbitrary[String]
//      payload   <- arbitrary[String]
//      signature <- arbitrary[String]
//    } yield JwtToken(s"$header.$payload.$signature")
//  }
}
