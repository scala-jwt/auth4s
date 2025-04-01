import sbt.*

object Dependencies {

  lazy val scalaTestV          = "3.2.19"
  lazy val scalaTestPlusCheckV = "3.2.18.0"
  lazy val scalacheckV         = "1.18.1"
  lazy val configV             = "1.4.3"
  lazy val circeV              = "0.14.12"
  lazy val jsoniterScalaV      = "2.33.2"
  lazy val catsV               = "2.13.0"
  lazy val jjwtV               = "0.12.6"
  lazy val catsEffectV         = "3.6.0"

  // Testing
  lazy val scalaTest               = "org.scalatest"     %% "scalatest"       % scalaTestV
  lazy val scalaTestPlusScalaCheck = "org.scalatestplus" %% "scalacheck-1-17" % scalaTestPlusCheckV
  lazy val scalacheck              = "org.scalacheck"    %% "scalacheck"      % scalacheckV

  // Circe
//  lazy val circeCore    = "io.circe" %% "circe-core"    % circeV
//  lazy val circeGeneric = "io.circe" %% "circe-generic" % circeV
//  lazy val circeParser  = "io.circe" %% "circe-parser"  % circeV

  // Jsoniter-scala
//  lazy val jsoniterScalacore = "com.github.plokhotnyuk.jsoniter-scala" %% "jsoniter-scala-core" % jsoniterScalaV
//  lazy val jsoniterScalamacros =
//    "com.github.plokhotnyuk.jsoniter-scala" %% "jsoniter-scala-macros" % jsoniterScalaV % "provided"

  // JJWT
  lazy val jjwtApi     = "io.jsonwebtoken" % "jjwt-api"     % jjwtV
  lazy val jjwtImpl    = "io.jsonwebtoken" % "jjwt-impl"    % jjwtV % "runtime"
  lazy val jjwtJackson = "io.jsonwebtoken" % "jjwt-jackson" % jjwtV % "runtime"

  // Typelevel
  lazy val catsCore   = "org.typelevel" %% "cats-core"   % catsV
  lazy val catsEffect = "org.typelevel" %% "cats-effect" % catsEffectV

  lazy val typesafeConfig = "com.typesafe" % "config" % configV
}
