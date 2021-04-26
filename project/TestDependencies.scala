import sbt._

object TestVersions {
  final lazy val approval    = "0.13.2"
  final lazy val pprint      = "0.6.4"
  final lazy val scalaCompat = "2.4.2"
  final lazy val scalatest   = "3.2.8"
}

object TestDependencies {
  final lazy val approval    = "com.colisweb"            % "approvals-java"          % TestVersions.approval    % Test
  final lazy val pprint      = "com.lihaoyi"            %% "pprint"                  % TestVersions.pprint      % Test
  final lazy val scalaCompat = "org.scala-lang.modules" %% "scala-collection-compat" % TestVersions.scalaCompat % Test
  final lazy val scalatest   = "org.scalatest"          %% "scalatest"               % TestVersions.scalatest   % Test
}
