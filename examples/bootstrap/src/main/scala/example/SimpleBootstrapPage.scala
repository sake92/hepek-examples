package example

import scalatags.Text.all._
import ba.sake.hepek.bootstrap3.statik.BootstrapStaticPage
import ba.sake.hepek.html.structure.SiteSettings

object SimpleBootstrapPage extends BootstrapStaticPage {

  override def pageTitle    = "awww-some title"
  override def siteSettings = SiteSettings("my.site", SimpleBootstrapPage)

  //override def fluidContainer = false
  override def withBootstrapNavbar = false

  override def pageContent = div(
    div(cls := "page-header")(
      h1("Example Page Header")
    ),
    div(cls := "jumbotron")(
      h2("Hello world!"),
      p("Some content...")
    )
  )

}
