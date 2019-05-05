import com.typesafe.sbt.web.Import.WebKeys

scalaVersion in ThisBuild := "2.12.8"

scalafmtOnCompile in ThisBuild := true

lazy val root = (project in file("."))
  .settings(
    libraryDependencies ++= Seq(
      "ba.sake" %% "hepek" % "0.3.1"
    ),
    (hepek in Compile) := {
      WebKeys.assets.value // run 'assets' after compiling
      (hepek in Compile).value
    },
    WebKeys.webModulesLib := "examples/lib"
  )
  .enablePlugins(HepekPlugin, SbtWeb)
