import com.typesafe.sbt.web.Import.WebKeys

scalaVersion in ThisBuild := "2.12.8"

scalafmtOnCompile in ThisBuild := true

resolvers in ThisBuild += Resolver.sonatypeRepo("snapshots")

lazy val root = (project in file("."))
  .settings(
    libraryDependencies ++= Seq(
      "ba.sake" %% "hepek" % "0.3.1+49-a42b291e-SNAPSHOT"
    ),
    (hepek in Compile) := {
      WebKeys.assets.value // run 'assets' after compiling
      (hepek in Compile).value
    },
    WebKeys.webModulesLib := "examples/lib",
    // gh pages stuff
    git.remoteRepo := "git@github.com:sake92/hepek-examples.git",
    ghpagesNoJekyll := true,
    siteSourceDirectory := target.value / "web" / "public" / "main" / "examples"
  )
  .enablePlugins(HepekPlugin, SbtWeb)
  .enablePlugins(GhpagesPlugin)
