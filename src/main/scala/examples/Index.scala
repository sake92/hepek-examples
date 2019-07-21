package examples

import scalatags.Text.all._
import examples.Imports._
import examples.simple._
import examples.statik.SimpleStaticPage
import examples.grid.GridExample
import examples.form.FormExample
import examples.math.MathJaxExample
import examples.math.KatexExample
import examples.prismjs.PrismJSExample
import examples.markdown.MarkdownExample

object Index extends StaticPage with Grid {

  val examples = List(
    ("simple", List(TextFile, JsonFile, RelPathExample)),
    ("static web page", List(SimpleStaticPage)),
    ("grid", List(GridExample)),
    ("code highlighting", List(PrismJSExample)),
    ("math", List(MathJaxExample, KatexExample)),
    ("forms", List(FormExample)),
    ("markdown", List(MarkdownExample))
  )

  override def pageSettings =
    super.pageSettings.withTitle("Hepek examples")

  override def pageContent = row(
    third(),
    third(
      div(cls := "page-header")(
        h1("Examples")
      ),
      table(cls := "table table-hover")(
        thead(
          th("Topic"),
          th("Examples")
        ),
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
                    .flatMap(content => List(br(), content))
                    .tail
                )
              )

          }
        )
      )
    ),
    third()
  )

}
