package examples.pdf

import java.io.File
import scalatags.Text.all._
import ba.sake.hepek.prismjs.PrismDependencies
import ba.sake.hepek.prismjs.Themes
import ba.sake.hepek.pdf.PdfGenerator
import examples.Imports.Bundle._
import org.openqa.selenium.chrome.ChromeDriver

object PdfStaticPage extends StaticPage with PrismDependencies {

  override def prismSettings =
    super.prismSettings
      .withTheme(Themes.Okaidia)
      .withLanguages(List("core", "clike", "java", "scala"))

  override def pageContent =
    """
      ## PDF Example
      Please run `sbt pdfGenerate`, Netlify doesn't have a Chrome installed... :D
      
      ```scala
      val x = 4
      println(x)
      ```

      [Click here to see PDF](example.pdf)
    """.md
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

    val driver = new ChromeDriver()
    new PdfGenerator(driver)
      .generate(file, targetFolder, List(PdfStaticPage))
  }
}
