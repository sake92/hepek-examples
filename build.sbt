import com.typesafe.sbt.web.Import.WebKeys

scalaVersion in ThisBuild := "2.12.8"

scalafmtOnCompile in ThisBuild := true

resolvers in ThisBuild += Resolver.sonatypeRepo("snapshots")

lazy val root = (project in file("."))
  .settings(
    libraryDependencies ++= Seq(
      "ba.sake" %% "hepek" % "0.4.0+36-5fc33a7f-SNAPSHOT" changing ()
    ),
    (hepek in Compile) := {
      WebKeys.assets.value // run 'assets' after compiling
      (hepek in Compile).value
    },
    WebKeys.webModulesLib := "examples/lib",
    // gh pages stuff
    git.remoteRepo := "git@github.com:sake92/hepek-examples.git",
    ghpagesNoJekyll := true,
    siteSourceDirectory := target.value / "web" / "public" / "main" / "examples",
    openIndexPage := openIndexPageTask.value
  )
  .enablePlugins(HepekPlugin, SbtWeb, GhpagesPlugin)

val openIndexPage = taskKey[Unit]("Opens index.html")

val openIndexPageTask = Def.taskDyn {
  Def.task {
    java.awt.Desktop
      .getDesktop()
      .browse(new File(hepekTarget.value + "/examples/index.html").toURI)
  }
}
