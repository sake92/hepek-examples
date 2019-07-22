package examples.statik

import scalatags.Text.all._
import examples.Imports._

object SimpleStaticPage extends StaticPage {

  // override def bootstrapContainer = "container"

  override def pageSettings =
    super.pageSettings.withTitle("Simple static page")

  override def pageContent = div(
    div(cls := "page-header")(
      h1("Example Page Header")
    ),
    div(cls := "jumbotron")(
      h2("Hello world!"),
      "Some content..."
    )
  )

}
