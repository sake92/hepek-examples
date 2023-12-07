package files.multi.basic

import java.nio.file.Paths
import ba.sake.hepek.path.ScalaMultiRenderable
import files.Bundle.*, Tags.*

object MyMultiPages extends ScalaMultiRenderable:
  override def rends =
    List(1, 2, 3).map(x => MyMultiPage(x))

class MyMultiPage(num: Int) extends StaticPage:

  override def navbar = None

  override def relPath =
    Paths.get(s"files/basic/page-$num.html")

  override def pageContent =
    div(s"This is page number $num")
