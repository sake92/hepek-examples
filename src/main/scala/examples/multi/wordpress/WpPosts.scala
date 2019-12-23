package examples.multi.wordpress

import java.nio.file.Paths
import org.jsoup.Jsoup
import scalatags.Text.all._
import com.afrozaar.wordpress.wpapi.{v2 => wp}
import ba.sake.hepek.path.ScalaMultiRenderable
import examples.Imports._
import examples.grid1._

// Template for single post
case class WpPost(wpPost: wp.model.Post) extends StaticPage {

  // we unescape HTML chars with jsoup
  private val wpPostTitle = Jsoup.parse(wpPost.getTitle().getRendered()).text()

  override def relPath = Paths.get(s"examples/multi/wordpress/${wpPost.getSlug()}.html")

  override def pageSettings = super.pageSettings.withTitle(wpPostTitle.toString)

  override def pageContent = div(
    div(cls := "page-header", classes.txtAlignCenter)(
      h1(wpPostTitle)
    ),
    row(
      raw( // raw HTML from WP
        wpPost.getContent().getRendered()
      )
    )
  )
}

// Render  all posts
object WpPosts extends ScalaMultiRenderable {

  override def rends: Seq[WpPost] = WpData.posts map WpPost
}
