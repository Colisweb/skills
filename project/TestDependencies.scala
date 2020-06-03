import sbt._

object TestVersions {
  final lazy val approval  = "0.13.0"
  final lazy val pprint    = "0.5.9"
  final lazy val scalatest = "3.1.2"
}

object TestDependencies {
  final lazy val approval  = "com.github.writethemfirst" % "approvals-java" % TestVersions.approval  % Test
  final lazy val pprint    = "com.lihaoyi"              %% "pprint"         % TestVersions.pprint    % Test
  final lazy val scalatest = "org.scalatest"            %% "scalatest"      % TestVersions.scalatest % Test
}
