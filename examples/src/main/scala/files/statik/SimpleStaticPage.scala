package files.statik

import files.Bundle.*, Tags.*

object SimpleStaticPage extends StaticPage {

  // we dont want default navbar here
  override def navbar = None

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
