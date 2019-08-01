package examples

import scalatags.Text.all._
import examples.Imports._
import examples.simple._
import examples.statik._
import examples.grid.GridExample
import examples.form.FormExample
import examples.math._
import examples.prismjs.PrismJSExample
import examples.markdown.MarkdownExample
import examples.panel.PanelExample
import examples.pdf.PdfStaticPage

object Index extends StaticPage with Grid {

  val examples = List(
    ("simple", List(TextFile, JsonFile, RelPathExample)),
    ("static page", List(SimpleStaticPage, StaticPageWithNavbar)),
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

  override def screenRatios = super.screenRatios.withAll(
    Ratios().withSingle(1, 2, 1)
  )

  val leBundle =
    Imports.getClass().getInterfaces().map(_.getSimpleName).find(_.contains("Bundle")).get

  override def pageContent = row(
    div(cls := "page-header")(
      h1("Examples"),
      s"Current bundle used: **$leBundle**".md
    ),
    div(classes.tableResponsive)(
      table(classes.tableClass, classes.tableHoverable)(
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
                    .flatMap(content => List(br(), content)) // should be "intersperse".. :)
                    .tail
                )
              )

          }
        )
      )
    )
  )

}
