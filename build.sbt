import com.typesafe.sbt.web.Import.WebKeys

ThisBuild / scalaVersion := "3.3.1"

ThisBuild / scalafmtOnCompile := true

ThisBuild / resolvers +=
  "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"

lazy val root = (project in file("."))
  .settings(
    scalacOptions ++= Seq(
      "-deprecation",
      "-Yretain-trees",
      "-Wunused:all"
    ),
    libraryDependencies ++= Seq(
      "ba.sake"                %% "hepek"                    % "0.13.0+8-3aeb2ce3-SNAPSHOT",
      "org.scala-lang.modules" %% "scala-collection-contrib" % "0.3.0",
      "com.afrozaar.wordpress" % "wp-api-v2-client-java"     % "4.8.3"
    ),
    (Compile / hepek) := {
      WebKeys.assets.value // run 'assets' after compiling
      (Compile / hepek).value
    },
    WebKeys.webModulesLib := "examples/lib",
    pdfGenerate := pdfGenerateTask.value,
    openIndexPage := openIndexPageTask.value
  )
  .enablePlugins(HepekPlugin, SbtWeb)

val pdfGenerate   = taskKey[Unit]("Generate PDFs")
val openIndexPage = taskKey[Unit]("Open index.html")

val pdfGenerateTask = Def.taskDyn {
  (Compile / hepek).value // pages must be generated
  val targetFolder = hepekTarget.value
  Def.task {
    (Compile/ runMain).toTask(" examples.pdf.GenPdf " + targetFolder).value
  }
}

val openIndexPageTask = Def.taskDyn {
  Def.task {
    java.awt.Desktop
      .getDesktop()
      .browse(new File(hepekTarget.value + "/examples/index.html").toURI)
  }
}
