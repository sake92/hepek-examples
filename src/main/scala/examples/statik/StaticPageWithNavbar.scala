package examples.statik

import scalatags.Text.all._
import examples.Imports._
import examples.Index

object StaticPageWithNavbar extends StaticPage {

  // 1. DIY custom navbar, or just use Navbar...
  private val myNavbar = Navbar.withStyle(Navbar.Companion.Style.Default)

  override def siteSettings = // 2. set site name and image (optional)
    super.siteSettings
      .withName("Example")
      .withFaviconInverted("https://img.icons8.com/doodle/48/000000/football2.png")

  override def staticSiteSettings = // 3. set index page and main pages for navbar
    super.staticSiteSettings
      .withIndexPage(Index)
      .withMainPages(SimpleStaticPage, StaticPageWithNavbar)

  override def pageSettings =
    super.pageSettings.withTitle("Static page with navbar")

  override def pageContent =
    div(
      myNavbar.simple(
        brandUrl = staticSiteSettings.indexPage.map(_.ref).getOrElse("#"),
        brandName = siteSettings.name.map(" " + _),
        brandIconUrl = siteSettings.faviconInverted,
        right = navbarRight
      ),
      h2("Hello world, again!"),
      p("Some content...")
    )

  private def navbarRight = {
    val aLink  = SimpleStaticPage.ref
    val aTitle = SimpleStaticPage.pageSettings.title
    Seq(
      hyperlink(aLink)(aTitle),
      myNavbar.simpleNestedLink(
        raw("Nested <span class='caret'></span>"),
        Seq(hyperlink(aLink)(aTitle))
      )
    )
  }

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
