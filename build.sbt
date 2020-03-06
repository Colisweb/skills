name := "skills"
organization := "com.colisweb"
scalaVersion := "2.12.8"

releaseCommitMessage := s"[ci skip] Setting version to ${(version in ThisBuild).value}"

scalafmtOnCompile := true
scalafmtCheck := true
scalafmtSbtCheck := true

resolvers += Resolver.bintrayRepo("writethemfirst", "maven")

libraryDependencies ++= Seq(
  "org.scalactic"             %% "scalactic"     % "3.1.0",
  "org.scalatest"             %% "scalatest"     % "3.1.0" % Test,
  "com.github.writethemfirst" % "approvals-java" % "0.12.0" % Test,
  "com.lihaoyi"               %% "pprint"        % "0.5.6" % Test,
  "eu.timepit"                %% "refined"       % "0.9.13"
)

// Uncomment the next 2 lines for auto-approbation
//ThisBuild / Test / fork := true
//ThisBuild / Test / javaOptions += "-DAUTO_APPROVE=true"

bintrayOrganization := Some("colisweb")
licenses += ("Apache-2.0", url("http://www.apache.org/licenses/"))
