package examples.multi.basic

import java.nio.file.Paths
import scalatags.Text.all._
import ba.sake.hepek.path.ScalaMultiRenderable
import examples.Imports._

object MyMultiPages extends ScalaMultiRenderable {
  override def rends =
    List(1, 2, 3) map MyMultiPage
}

case class MyMultiPage(num: Int) extends StaticPage {
  override def relPath =
    Paths.get(s"examples/basic/page-$num.html")

  override def pageContent =
    div(s"This is page number $num")
}
