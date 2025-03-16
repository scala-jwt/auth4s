import Projects.*
import org.typelevel.sbt.gha.Permissions

Global / onChangedBuildSource := ReloadOnSourceChanges

ThisBuild / scalaVersion := "3.3.5"

ThisBuild / Test / fork := true
ThisBuild / run / fork := true
ThisBuild / Test / parallelExecution := false
ThisBuild / Test / testForkedParallel := true

ThisBuild / semanticdbEnabled := true
ThisBuild / semanticdbVersion := "4.8.15"

ThisBuild / tlBaseVersion := "0.0"
ThisBuild / organization := "io.github.scala-jwt"
ThisBuild / organizationName := "auth4s"
ThisBuild / organizationHomepage := Some(url("https://github.com/scala-jwt/auth4s"))
ThisBuild / tlMimaPreviousVersions := Set.empty
ThisBuild / licenses := Seq(License.Apache2)
ThisBuild / developers := List(
  tlGitHubDev("andrewrigas", "Andreas Rigas")
)
ThisBuild / sonatypeCredentialHost := xerial.sbt.Sonatype.sonatypeLegacy
ThisBuild / startYear := Some(2025)
ThisBuild / githubWorkflowPermissions := Some(Permissions.WriteAll)
ThisBuild / githubWorkflowJavaVersions := Seq("11", "17", "21").map(JavaSpec.temurin)
ThisBuild / githubWorkflowAddedJobs ++= Seq(
  WorkflowJob(
    id     = "checklint",
    name   = "Check code style",
    scalas = List(scalaVersion.value),
    steps = List(WorkflowStep.Checkout) ++ WorkflowStep.SetupJava(
      List(githubWorkflowJavaVersions.value.last)
    ) ++ githubWorkflowGeneratedCacheSteps.value ++ List(
      WorkflowStep.Sbt(
        List("checkLint"),
        name = Some("Check Scalafmt and Scalafix rules"),
      )
    ),
  ),
  WorkflowJob(
    id     = "Codecov",
    name   = "Codecov",
    scalas = List(scalaVersion.value),
    steps = List(WorkflowStep.Checkout) ++ WorkflowStep.SetupJava(
      List(githubWorkflowJavaVersions.value.last)
    ) ++ githubWorkflowGeneratedCacheSteps.value ++ List(
      WorkflowStep.Sbt(List("coverage", "test", "coverageAggregate")),
      WorkflowStep.Use(
        UseRef.Public(
          "codecov",
          "codecov-action",
          "v3.1.1",
        )
      ),
    ),
  ),
)

// Expose `DISABLE_SCALA_LINT_ON_COMPILE=true` environment variables in CI to disable scalafmt/scalafix on compile
ThisBuild / scalafixOnCompile := !sys.env.getOrElse("DISABLE_SCALA_LINT_ON_COMPILE", "false").toBoolean
ThisBuild / scalafmtOnCompile := !sys.env.getOrElse("DISABLE_SCALA_LINT_ON_COMPILE", "false").toBoolean

lazy val auth4s      = "auth4s"
lazy val modulesRoot = file("modules")

def createAuth4sModule(subModule: String): Project = {
  val moduleName = s"$auth4s-$subModule"
  val moduleFile = modulesRoot / moduleName
  Project(
    moduleName,
    moduleFile,
  )
}

lazy val root = Project(auth4s, file("."))
  .settings(Aliases.all)
  .aggregate(auth4sCore)

lazy val auth4sCore = createAuth4sModule("core")
  .withDependencies(
      Dependencies.typesafeConfig,
      Dependencies.catsCore,
      Dependencies.nimbusJoseJwt,
      Dependencies.scalaTest % Test,
      Dependencies.scalaTestPlusScalaCheck % Test,
      Dependencies.scalacheck % Test,
  )
