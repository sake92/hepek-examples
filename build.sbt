import com.typesafe.sbt.web.Import.WebKeys

scalaVersion in ThisBuild := "2.13.4"

scalafmtOnCompile in ThisBuild := true

lazy val root = (project in file("."))
  .settings(
    libraryDependencies ++= Seq(
      "ba.sake"                %% "hepek"                    % "0.8.9",
      "org.scala-lang.modules" %% "scala-collection-contrib" % "0.2.1",
      "com.afrozaar.wordpress" % "wp-api-v2-client-java"     % "4.8.3"
    ),
    (hepek in Compile) := {
      WebKeys.assets.value // run 'assets' after compiling
      (hepek in Compile).value
    },
    WebKeys.webModulesLib := "examples/lib",
    pdfGenerate := pdfGenerateTask.value
  )
  .enablePlugins(HepekPlugin, SbtWeb)

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
