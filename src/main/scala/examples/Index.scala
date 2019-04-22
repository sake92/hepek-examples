package examples

import scalatags.Text.all._
import ba.sake.hepek.bootstrap3.BootstrapBundle
import examples.simple._
import examples.statik.SimpleStaticPage
import examples.grid.GridExample
import examples.form.FormExample
import examples.mathjax.MathJaxExample
import examples.prismjs.PrismJSExample
import examples.markdown.MarkdownExample

object CustomBundle extends BootstrapBundle
import CustomBundle._

object Index extends StatikPage with Grid {

  val examples = List(
    ("simple", List(TextFile, JsonFile, RelPathExample)),
    ("static web page", List(SimpleStaticPage)),
    ("grid", List(GridExample)),
    ("prismjs", List(PrismJSExample)),
    ("mathjax", List(MathJaxExample)),
    ("forms", List(FormExample)),
    ("markdown", List(MarkdownExample))
  )

  override def pageSettings =
    super.pageSettings.withTitle("Hepek examples")

  override def pageContent = row(
    third1(),
    third2(
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
    third3()
  )

}
