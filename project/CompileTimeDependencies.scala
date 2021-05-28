import sbt._

object Versions {
  final lazy val refined = "0.9.26"
}

object CompileTimeDependencies {
  final lazy val refined = "eu.timepit" %% "refined" % Versions.refined
}
