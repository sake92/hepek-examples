import com.typesafe.sbt.web.Import.WebKeys

scalaVersion in ThisBuild := "2.12.4"
scalafmtOnCompile in ThisBuild := true

lazy val commonSettings = Seq(
  organization := "ba.sake",
  version := "0.0.0-SNAPSHOT",
  libraryDependencies ++= Seq(
    "ba.sake" %% "hepek" % "0.0.3-SNAPSHOT"
  ),
  resolvers += Resolver.sonatypeRepo("snapshots")
  // logLevel in hepek := Level.Debug // enable to see more detailed output
)

lazy val simpleExample = (project in file("examples/simple"))
  .settings(commonSettings)
  .enablePlugins(HepekPlugin, SbtWeb)

lazy val bootstrapExample = (project in file("examples/bootstrap"))
  .settings(commonSettings)
  .enablePlugins(HepekPlugin, SbtWeb)

lazy val prismjsExample = (project in file("examples/prismjs"))
  .settings(commonSettings)
  .enablePlugins(HepekPlugin, SbtWeb)
