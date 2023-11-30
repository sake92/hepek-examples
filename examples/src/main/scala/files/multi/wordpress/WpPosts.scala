package files.multi.wordpress

import java.nio.file.Paths
import com.afrozaar.wordpress.wpapi.{v2 => wp}
import org.jsoup.Jsoup
import scalatags.Text.all._
import ba.sake.hepek.path.ScalaMultiRenderable
import files.Imports.Bundle._, Grid._, Classes._

// Render all posts
object WpPosts extends ScalaMultiRenderable {

  override def rends: Seq[WpPost] = WpData.posts.map(WpPost.apply)
}

// Template for single post
case class WpPost(wpPost: wp.model.Post) extends StaticPage {

  // unescape HTML chars with Jsoup
  private val wpPostTitle = Jsoup.parse(wpPost.getTitle().getRendered()).text()

  override def relPath = Paths.get(s"files/multi/wordpress/${wpPost.getSlug()}.html")

  override def pageSettings = super.pageSettings.withTitle(wpPostTitle.toString)

  override def pageContent = div(
    div(cls := "page-header", txtAlignCenter)(
      h1(wpPostTitle)
    ),
    row(
      raw(
        wpPost.getContent().getRendered()
      )
    )
  )
}
