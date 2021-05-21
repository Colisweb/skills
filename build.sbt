import CompileFlags._

lazy val scala212               = "2.12.13"
lazy val scala213               = "2.13.6"
lazy val supportedScalaVersions = List(scala213, scala212)

name := "skills"
scalaVersion := scala213
scalafmtOnCompile := true
scalafmtCheck := true
scalafmtSbtCheck := true
ThisBuild / pushRemoteCacheTo := Some(
  MavenCache("local-cache", baseDirectory.value / sys.env.getOrElse("CACHE_PATH", "sbt-cache"))
)
scalacOptions ++= crossScalacOptions(scalaVersion.value)
crossScalaVersions := supportedScalaVersions

// Uncomment the next 2 lines for auto-approbation
//Test / fork := true
//Test / javaOptions += "-DAUTO_APPROVE=true"

libraryDependencies ++= Seq(
  CompileTimeDependencies.refined
) ++ Seq(
  TestDependencies.approval,
  TestDependencies.pprint,
  TestDependencies.scalaCompat,
  TestDependencies.scalatest
)







