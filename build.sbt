lazy val examples = (project in file("examples"))
  .settings(
    scalaVersion := "3.3.1",
    scalacOptions ++= Seq(
      "-deprecation",
      "-Yretain-trees",
      "-Wunused:all"
    ),
    libraryDependencies ++= Seq(
      "ba.sake" %% "hepek" % "0.20.0",
      "org.scala-lang.modules" %% "scala-collection-contrib" % "0.3.0",
      "com.afrozaar.wordpress" % "wp-api-v2-client-java" % "4.8.3"
    ),
    genPDF := genPDFTask.value
  )
  .enablePlugins(HepekPlugin)

val genPDF = taskKey[Unit]("Generate PDFs")

val genPDFTask = Def.taskDyn {
  (Compile / hepek).value
  val targetFolder = hepekTarget.value
  Def.task {
    (Compile / runMain).toTask(" files.pdf.GenPdf " + targetFolder).value
  }
}
