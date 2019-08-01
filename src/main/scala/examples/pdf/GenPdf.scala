package examples.pdf

import java.io.File
import scalatags.Text.all._
import ba.sake.hepek.bootstrap3.statik.BootstrapStaticBundle
import ba.sake.hepek.prismjs.PrismDependencies
import ba.sake.hepek.prismjs.PrismCodeHighlightComponents
import ba.sake.hepek.prismjs.Themes
import ba.sake.hepek.pdf.PdfGenerator

object Imports extends BootstrapStaticBundle
import Imports._

object chl extends PrismCodeHighlightComponents

object PdfStaticPage extends StaticPage with PrismDependencies {

  override def prismSettings =
    super.prismSettings
      .withTheme(Themes.Okaidia)
      .withLanguages(List("core", "clike", "java", "scala"))

  override def pageContent = frag(
    h2("PDF Example"),
    p("Please run `sbt pdfGenerate`, Netlify doesn't have a Chrome installed... :D"),
    chl.scala("""
    val x = 4
    println(x)
    """),
    a(href := "example.pdf")("Click here to see PDF")
  )
}

object GenPdf {

  def main(args: Array[String]): Unit = {
    if (args.isEmpty) {
      println(
        "You must pass the targetFolder value (base folder for generated pages)..."
      )
    }
    val targetFolder = args(0)
    val file         = new File(s"$targetFolder/examples/pdf/example.pdf")
    System
      .setProperty(
        "webdriver.chrome.driver",
        "C:/hepek/chromedriver_win32/chromedriver.exe"
      )

    PdfGenerator
      .generate(file, targetFolder, List(PdfStaticPage))
  }
}
