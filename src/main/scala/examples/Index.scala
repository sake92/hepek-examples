package examples

import scala.collection.decorators._
import scalatags.Text.all._
import examples.simple._
import examples.statik._
import examples.grid.GridExample
import examples.form.FormExample
import examples.math._
import examples.markdown.MarkdownExample
import examples.prismjs.PrismJSExample
import examples.panel.PanelExample
import examples.pdf.PdfStaticPage
import examples.multi.wordpress.WpPostsLists
import examples.multi.basic.MyMultiPages
import Imports.Bundle._, Grid._, Classes._

object Index extends StaticPage {

  val examples = List(
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

  override def pageContent = row(
    div(cls := "page-header")(
      h1("Examples"),
      s"""
        Current bundle used: **${Imports.Bundle.getClass.getSimpleName}**
        
        Source code is [here](https://github.com/sake92/hepek-examples)
      """.md
    ),
    div(tableResponsive)(
      table(tableClass, tableHoverable, tableWidthFull)(
        thead(th("Topic"), th("Examples")),
        tbody(
          examples.map {
            case (exTitle, exPages) =>
              tr(
                td(exTitle),
                td(
                  exPages
                    .map { page =>
                      a(href := relTo(page))(
                        page.getClass.getSimpleName.replaceAll("\\$", "")
                      )
                    }
                    .intersperse(br())
                )
              )
          }
        )
      )
    )
  )

}
