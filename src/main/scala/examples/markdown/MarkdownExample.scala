package examples.markdown

import scalatags.Text.all._
import ba.sake.hepek.bootstrap3.statik.BootstrapStaticPage
import ba.sake.hepek.html.component.BasicComponents

object MarkdownExample extends BootstrapStaticPage with BasicComponents {

  override def pageSettings =
    super.pageSettings
      .withTitle("Markdown Example")

  override def pageContent = frag(
    div(cls := "page-header text-center")(
      h1("Markdown Example")
    ),
    div(cls := "well well-lg ")(
      s"""
      Here is some test to see that it is being displayed in Markdown

      # This is a header.
    """.md,
    )
  )
}
