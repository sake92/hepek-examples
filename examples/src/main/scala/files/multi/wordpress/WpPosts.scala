package files.multi.wordpress

import java.nio.file.Paths
import com.afrozaar.wordpress.wpapi.v2.model.Post
import org.jsoup.Jsoup
import ba.sake.hepek.path.ScalaMultiRenderable
import files.Bundle.*, Tags.*

// Render all posts
object WpPosts extends ScalaMultiRenderable:
  override def rends: Seq[WpPost] =
    WpData.posts.map(p => WpPost(p))

// Template for single post
class WpPost(wpPost: Post) extends StaticPage {
  override def navbar = None

  // unescape HTML chars with Jsoup
  private val wpPostTitle = Jsoup.parse(wpPost.getTitle().getRendered()).text()

  override def relPath = Paths.get(s"files/multi/wordpress/${wpPost.getSlug()}.html")

  override def pageSettings = super.pageSettings.withTitle(wpPostTitle.toString)

  override def pageContent = div(
    div(cls := "page-header", Classes.txtAlignCenter)(
      h1(wpPostTitle)
    ),
    Grid.row(
      raw(
        wpPost.getContent().getRendered()
      )
    )
  )
}
