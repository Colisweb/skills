import CompileFlags._

lazy val scala212               = "2.12.11"
lazy val scala213               = "2.13.1"
lazy val supportedScalaVersions = List(scala213, scala212)

name := "skills"
scalaVersion := scala213
organization := "com.colisweb"
scalafmtOnCompile := true
scalafmtCheck := true
scalafmtSbtCheck := true
bintrayOrganization := Some("colisweb")
licenses += ("Apache-2.0", url("http://www.apache.org/licenses/"))
scalacOptions ++= crossScalacOptions(scalaVersion.value)
crossScalaVersions := supportedScalaVersions

// Uncomment the next 2 lines for auto-approbation
//Test / fork := true
//Test / javaOptions += "-DAUTO_APPROVE=true"

resolvers += Resolver.bintrayRepo("writethemfirst", "maven")

libraryDependencies ++= Seq(
  CompileTimeDependencies.refined
) ++ Seq(
  TestDependencies.approval,
  TestDependencies.pprint,
  TestDependencies.scalaCompat,
  TestDependencies.scalatest
)







