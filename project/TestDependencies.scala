import sbt._

object TestVersions {
  final lazy val approval    = "0.13.1"
  final lazy val pprint      = "0.5.9"
  final lazy val scalaCompat = "2.1.6"
  final lazy val scalatest   = "3.2.0"
}

object TestDependencies {
  final lazy val approval    = "com.github.writethemfirst" % "approvals-java"          % TestVersions.approval    % Test
  final lazy val pprint      = "com.lihaoyi"              %% "pprint"                  % TestVersions.pprint      % Test
  final lazy val scalaCompat = "org.scala-lang.modules"   %% "scala-collection-compat" % TestVersions.scalaCompat % Test
  final lazy val scalatest   = "org.scalatest"            %% "scalatest"               % TestVersions.scalatest   % Test
}
