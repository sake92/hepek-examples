import com.typesafe.sbt.web.Import.WebKeys

scalaVersion in ThisBuild := "2.12.4"
scalafmtOnCompile in ThisBuild := true

lazy val commonSettings = Seq(
  organization := "ba.sake",
  version := "0.0.0-SNAPSHOT",
  resolvers += Resolver.sonatypeRepo("snapshots"),
  libraryDependencies ++= Seq(
    "ba.sake" %% "hepek" % "0.1.2-SNAPSHOT"
  )
  //, logLevel in hepek := Level.Debug // enable to see more detailed output
)

lazy val simpleExample = (project in file("examples/simple"))
  .settings(commonSettings)
  .enablePlugins(HepekPlugin)

lazy val bootstrapExample = (project in file("examples/bootstrap"))
  .settings(commonSettings)
  .enablePlugins(HepekPlugin)

lazy val formExample = (project in file("examples/form"))
  .settings(commonSettings)
  .enablePlugins(HepekPlugin)

lazy val prismjsExample = (project in file("examples/prismjs"))
  .settings(commonSettings)
  .enablePlugins(HepekPlugin)

lazy val mathjaxExample = (project in file("examples/mathjax"))
  .settings(commonSettings)
  .enablePlugins(HepekPlugin)
