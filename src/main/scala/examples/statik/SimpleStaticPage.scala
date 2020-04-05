package examples.statik

import scalatags.Text.all._
import examples.Imports.Bundle._

object SimpleStaticPage extends StaticPage {

  override def pageSettings =
    super.pageSettings.withTitle("Simple static page")

  override def pageContent = div(
    """
      # Example Page Header

      ---
      ## Hello world!

      "Some content..."
    """.md
  )

}
