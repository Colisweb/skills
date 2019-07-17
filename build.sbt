name := "skills"
organization := "com.colisweb"
version := "0.1"
scalaVersion := "2.12.8"

scalafmtOnCompile := true
scalafmtCheck := true
scalafmtSbtCheck := true

coverageEnabled := true

libraryDependencies ++= Seq(
  "org.scalactic" %% "scalactic" % "3.0.8",
  "org.scalatest" %% "scalatest" % "3.0.8" % "test"
)
