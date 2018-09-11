package examples

import scalatags.Text.all._
import ba.sake.hepek.bootstrap3.statik.BootstrapStaticPage
import examples.bootstrap._
import examples.form.FormExample
import examples.mathjax.MathJaxExample
import examples.prismjs.PrismJSExample
import examples.simple._

object Index extends BootstrapStaticPage {

  val examples = List(
    ("simple", List(TextFile, JsonFile, RelPathExample)),
    ("bootstrap", List(SimpleBootstrapPage, BootstrapGridExample)),
    ("prismjs", List(PrismJSExample)),
    ("mathjax", List(MathJaxExample)),
    ("forms", List(FormExample))
  )

  override def pageSettings =
    super.pageSettings
      .withTitle("Hepek examples")

  override def pageContent = div(
    div(cls := "page-header")(
      h1("Examples")
    ),
    table(
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
                exPages.map(page => a(href := relTo(page))(page.getClass.getSimpleName))
              )
            )

        }
      )
    )
  )

}
