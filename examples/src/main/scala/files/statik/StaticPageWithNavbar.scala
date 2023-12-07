package files.statik

import scalatags.Text.all._
import files.Imports.Bundle._
import files.Index

object StaticPageWithNavbar extends StaticPage {

  override def siteSettings =
    super.siteSettings
      .withName("Example")
      .withFaviconInverted("https://img.icons8.com/doodle/48/000000/football2.png")

  override def staticSiteSettings =
    super.staticSiteSettings
      .withIndexPage(Index)
      .withMainPages(SimpleStaticPage, StaticPageWithNavbar)

  override def pageSettings =
    super.pageSettings.withTitle("Static page with navbar")

  override def pageContent =
    div(
      Navbar.nav(
        brandUrl = staticSiteSettings.indexPage.map(_.ref).getOrElse("#"),
        brandName = siteSettings.name.map(" " + _),
        brandIconUrl = siteSettings.faviconInverted,
        right = Seq(
          a(href := SimpleStaticPage.ref)(SimpleStaticPage.pageSettings.label),
          Navbar.dropdown(
            "Nested",
            Seq(
              Navbar.dropdownLink(SimpleStaticPage.ref, SimpleStaticPage.pageSettings.label)
            )
          )
        )
      ),
      h2("Hello world, again!"),
      p("Some content...")
    )

  override def stylesInline =
    super.stylesInline :+
      """
        body {
          padding-top: 33px; /* move from navbar */
        }
        .navbar-brand > span > img {
          height: 30px; /* make logo smaller */
        }
      """

}
