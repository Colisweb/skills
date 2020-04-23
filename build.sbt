name := "skills"
organization := "com.colisweb"
scalaVersion := "2.12.10"

scalafmtOnCompile := true
scalafmtCheck := true
scalafmtSbtCheck := true

resolvers += Resolver.bintrayRepo("writethemfirst", "maven")

libraryDependencies ++= Seq(
  "org.scalatest"             %% "scalatest"     % "3.1.1" % Test,
  "com.github.writethemfirst" % "approvals-java" % "0.13.0" % Test,
  "com.lihaoyi"               %% "pprint"        % "0.5.9" % Test,
  "eu.timepit"                %% "refined"       % "0.9.14"
)

// Uncomment the next 2 lines for auto-approbation
//ThisBuild / Test / fork := true
//ThisBuild / Test / javaOptions += "-DAUTO_APPROVE=true"

bintrayOrganization := Some("colisweb")
licenses += ("Apache-2.0", url("http://www.apache.org/licenses/"))
