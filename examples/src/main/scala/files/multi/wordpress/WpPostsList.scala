package files.multi.wordpress

import java.nio.file.Paths

import ba.sake.hepek.path.ScalaMultiRenderable
import files.Imports.Bundle.*, Tags.*

case class ResultPage[T](page: Int, perPage: Int, items: Seq[T])

// Posts lists
object WpPostsLists extends ScalaMultiRenderable:
  override def rends =
    WpPosts.rends
      .grouped(WpData.perPage)
      .zipWithIndex
      .map { case (wpPosts, i) =>
        val resultPage = ResultPage(i + 1, WpData.perPage, wpPosts)
        WpPostsList(resultPage)
      }
      .toSeq

// Posts list page
class WpPostsList(resultPage: ResultPage[WpPost]) extends StaticPage {
  override def navbar = None

  override def relPath = Paths.get(s"files/multi/wordpress/page${resultPage.page}.html")

  override def pageSettings = super.pageSettings.withTitle(s"Page ${resultPage.page}")

  private val hasPrev = resultPage.page > 1
  private val hasNext = resultPage.page < WpData.pageCount

  override def pageContent = div(
    div(cls := "page-header", Classes.txtAlignCenter)(
      s"# Posts page ${resultPage.page}/${WpData.pageCount}".md
    ),
    Grid.row(
      s"Posts fetched from public WP API of page ${WpData.baseUrl} (hope they don't mind)".md,
      hr,
      ul(
        resultPage.items.map { wpPost =>
          li(
            a(href := wpPost.ref)(wpPost.pageSettings.title)
          )
        }
      ),
      hr,
      Option.when(hasPrev) {
        a(href := s"./page${resultPage.page - 1}.html")("Prev page")
      },
      Option.when(hasPrev && hasNext) { " | " },
      Option.when(hasNext) {
        a(href := s"./page${resultPage.page + 1}.html")("Next page")
      }
    )
  )
}
