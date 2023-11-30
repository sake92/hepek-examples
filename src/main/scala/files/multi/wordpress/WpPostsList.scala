package files.multi.wordpress

import java.nio.file.Paths
import scalatags.Text.all._
import ba.sake.hepek.path.ScalaMultiRenderable
import files.Imports.Bundle._, Grid._, Classes._

case class ResultPage[T](page: Int, perPage: Int, items: Seq[T])

// Posts lists
object WpPostsLists extends ScalaMultiRenderable {

  val perPage = 10
  val pageCount = WpPosts.rends.length / perPage

  override def rends =
    WpPosts.rends
      .grouped(perPage)
      .zipWithIndex
      .map { case (wpPosts, i) =>
        val resultPage = ResultPage(i + 1, perPage, wpPosts)
        WpPostsList(resultPage)
      }
      .toSeq
}

// Style single page based on posts paging
case class WpPostsList(resultPage: ResultPage[WpPost]) extends StaticPage {

  override def relPath = Paths.get(s"files/multi/wordpress/page${resultPage.page}.html")

  override def pageSettings = super.pageSettings.withTitle(s"Page ${resultPage.page}")

  private val hasPrev = resultPage.page > 1
  private val hasNext = resultPage.page < WpPostsLists.pageCount

  override def pageContent = div(
    div(cls := "page-header", txtAlignCenter)(
      s"# Posts page ${resultPage.page}/${WpPostsLists.pageCount}".md
    ),
    row(
      s"Posts fetched from public WP API of page ${WpClient.baseUrl} (hope they don't mind)".md,
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
