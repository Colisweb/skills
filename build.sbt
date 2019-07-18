name := "skills"
organization := "com.colisweb"
version := "1.0.0"
scalaVersion := "2.12.8"

scalafmtOnCompile := true
scalafmtCheck := true
scalafmtSbtCheck := true

coverageEnabled := true

resolvers += Resolver.bintrayRepo("writethemfirst", "maven")


libraryDependencies ++= Seq(
  "org.scalactic" %% "scalactic" % "3.0.8",
  "org.scalatest" %% "scalatest" % "3.0.8" % "test",
  "com.github.writethemfirst" % "approvals-java" % "0.9.0" % Test,
  "com.lihaoyi" %% "pprint" % "0.5.3" % Test,
)

// Uncomment the next 2 lines for auto-approbation
//ThisBuild / Test / fork := true
//ThisBuild / Test / javaOptions += "-DAUTO_APPROVE=true"

bintrayOrganization := Some("colisweb")
licenses += ("Apache-2.0", url("http://www.apache.org/licenses/"))
