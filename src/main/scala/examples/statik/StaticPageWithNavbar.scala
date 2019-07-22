package examples.statik

import scalatags.Text.all._
import examples.Imports._
import examples.Index

object customNavbar extends Navbar {
  import Navbar._
  // override def navbarStyle = Style.Inverse
}

object StaticPageWithNavbar extends StaticPage {

  override def navbar = Some(customNavbar) // 1. enable navbar

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
