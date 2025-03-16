import sbt.*

object Dependencies {

  lazy val scalaTestV          = "3.2.19"
  lazy val scalaTestPlusCheckV = "3.2.18.0"
  lazy val scalacheckV         = "1.18.1"
  lazy val configV             = "1.4.3"
  lazy val circeV              = "0.14.11"
  lazy val jsoniterScalaV      = "2.33.2"
  lazy val catsV               = "2.13.0"
  lazy val NimbusdsV           = "10.0.2"

  // Testing
  lazy val scalaTest               = "org.scalatest"     %% "scalatest"       % scalaTestV
  lazy val scalaTestPlusScalaCheck = "org.scalatestplus" %% "scalacheck-1-17" % scalaTestPlusCheckV
  lazy val scalacheck              = "org.scalacheck"    %% "scalacheck"      % scalacheckV

  // Circe
  lazy val circeCore    = "io.circe" %% "circe-core"    % circeV
  lazy val circeGeneric = "io.circe" %% "circe-generic" % circeV
  lazy val circeParser  = "io.circe" %% "circe-parser"  % circeV

  // Jsoniter-scala
  lazy val jsoniterScalacore = "com.github.plokhotnyuk.jsoniter-scala" %% "jsoniter-scala-core" % jsoniterScalaV
  lazy val jsoniterScalamacros =
    "com.github.plokhotnyuk.jsoniter-scala" %% "jsoniter-scala-macros" % jsoniterScalaV % "provided"

  // Nimbusds
  lazy val nimbusJoseJwt = "com.nimbusds" % "nimbus-jose-jwt" % NimbusdsV

  // Typelevel
  lazy val catsCore = "org.typelevel" %% "cats-core" % catsV

  lazy val typesafeConfig = "com.typesafe" % "config" % configV
}
