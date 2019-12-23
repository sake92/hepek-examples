package examples.multi.wordpress

import java.nio.file.Paths
import scala.collection.JavaConverters._
import scalatags.Text.all._
import com.afrozaar.wordpress.wpapi.{v2 => wp}
import ba.sake.hepek.path.ScalaMultiRenderable
import examples.Imports._

// helper classes
case class ResultPage[T](page: Int, perPage: Int, items: Seq[T])

object grid2 extends Grid {
  override def screenRatios =
    super.screenRatios.withAll(Ratios().withSingle(1, 3, 1))
}
import grid2._

// 1. Style your page based on WP post data
case class WpPostsList(resultPage: ResultPage[WpPost]) extends StaticPage {

  override def relPath = Paths.get(s"examples/multi/wordpress/page${resultPage.page}.html")

  override def pageSettings = super.pageSettings.withTitle(s"Page ${resultPage.page}")

  private val hasPrev = resultPage.page > 1
  private val hasNext = resultPage.page < WpPostsLists.pageCount

  override def pageContent = div(
    div(cls := "page-header", classes.txtAlignCenter)(
      h1(s"Posts page ${resultPage.page}/${WpPostsLists.pageCount}")
    ),
    row(
      div(
        "Posts fetched from public WP API of page https://howtodoinjava.com (hope they don't mind)"
      ),
      hr,
      ul(
        resultPage.items.map { wpPost =>
          li(
            hyperlink(wpPost.ref)(wpPost.pageSettings.title)
          )
        }
      ),
      hr,
      if (hasPrev)
        hyperlink(s"./page${resultPage.page - 1}.html")("Prev page")
      else frag(),
      if (hasPrev && hasNext) " | " else frag(),
      if (hasNext)
        hyperlink(s"./page${resultPage.page + 1}.html")("Next page")
      else frag()
    )
  )
}

// 3. Fetch and render posts as you like :)
object WpPostsLists extends ScalaMultiRenderable {

  val perPage   = 10
  val pageCount = WpPosts.rends.length / perPage

  override def rends =
    WpPosts.rends
      .grouped(perPage)
      .zipWithIndex
      .map {
        case (wpPosts, i) =>
          val resultPage = ResultPage(i + 1, perPage, wpPosts)
          WpPostsList(resultPage)
      }
      .toSeq
}
