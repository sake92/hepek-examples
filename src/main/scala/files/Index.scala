package files

import scala.collection.decorators._
import scalatags.Text.all._
import files.simple._
import files.statik._
import files.grid.GridExample
import files.form.FormExample
import files.math._
import files.markdown.MarkdownExample
import files.prismjs.PrismJSExample
import files.panel.PanelExample
import files.pdf.PdfStaticPage
import files.multi.wordpress.WpPostsLists
import files.multi.basic.MyMultiPages
import Imports.Bundle.*

object Index extends StaticPage {

  val allExamples = List(
    ("single file", List(TextFile, JsonFile, RelPathExample)),
    ("multiple files", List(MyMultiPages.rends.head, WpPostsLists.rends.head)),
    ("static web page", List(SimpleStaticPage, StaticPageWithNavbar)),
    ("grid", List(GridExample)),
    ("code highlighting", List(PrismJSExample)),
    ("math", List(MathJaxExample, KatexExample)),
    ("forms", List(FormExample)),
    ("markdown", List(MarkdownExample)),
    ("panel", List(PanelExample)),
    ("pdf", List(PdfStaticPage))
  )

  override def pageSettings =
    super.pageSettings.withTitle("Hepek examples")

  override def pageContent = Grid.row(
    div(cls := "page-header")(
      h1("Examples"),
      s"""
        Current bundle used: **${Imports.Bundle.getClass.getSimpleName}**
        
        Source code is [here](https://github.com/sake92/hepek-examples)
      """.md
    ),
    div(Classes.tableResponsive)(
      table(Classes.tableClass, Classes.tableHoverable, Classes.tableWidthFull)(
        thead(th("Topic"), th("Examples")),
        tbody(
          allExamples.map { case (exTitle, exPages) =>
            val links = exPages
              .map { page =>
                a(href := relTo(page))(
                  page.getClass.getSimpleName.replaceAll("\\$", "")
                )
              }
              .intersperse(br())
            tr(
              td(exTitle),
              td(links)
            )
          }
        )
      )
    )
  )

}
