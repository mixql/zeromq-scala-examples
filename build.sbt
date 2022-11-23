val scala3Version = "2.12.15"

lazy val root = project
  .in(file("."))
  .settings(
    name := "zeromq-scala-examples",
    version := "0.1.0-SNAPSHOT",

    scalaVersion := scala3Version,

    libraryDependencies ++= Seq(
      "org.scalameta" %% "munit" % "0.7.29" % Test,
      "org.zeromq" % "jeromq" % "0.5.2"
    )
  )
