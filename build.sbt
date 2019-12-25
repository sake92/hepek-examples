import com.typesafe.sbt.web.Import.WebKeys

scalaVersion in ThisBuild := "2.13.1"

scalafmtOnCompile in ThisBuild := true

resolvers in ThisBuild += Resolver.sonatypeRepo("snapshots")
resolvers in ThisBuild += Resolver.bintrayRepo("mpollmeier", "maven")

lazy val root = (project in file("."))
  .settings(
    libraryDependencies ++= Seq(
      "ba.sake" %% "hepek" % "0.6.0", //changing (),
      "com.michaelpollmeier" %% "scala-collection-contrib" % "0.2.1",
      "com.afrozaar.wordpress" % "wp-api-v2-client-java" % "4.8.3"
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
    pdfGenerate := pdfGenerateTask.value,
    openIndexPage := openIndexPageTask.value
  )
  .enablePlugins(HepekPlugin, SbtWeb, GhpagesPlugin)

val pdfGenerate   = taskKey[Unit]("Generate PDFs")
val openIndexPage = taskKey[Unit]("Open index.html")

val pdfGenerateTask = Def.taskDyn {
  (hepek in Compile).value // pages must be generated
  val targetFolder = hepekTarget.value
  Def.task {
    (runMain in Compile).toTask(" examples.pdf.GenPdf " + targetFolder).value
  }
}

val openIndexPageTask = Def.taskDyn {
  Def.task {
    java.awt.Desktop
      .getDesktop()
      .browse(new File(hepekTarget.value + "/examples/index.html").toURI)
  }
}
