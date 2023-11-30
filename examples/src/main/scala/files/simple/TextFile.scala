package files.simple

import java.nio.file.Paths
import ba.sake.hepek.core.Renderable

object TextFile extends Renderable {

  // we EXPLICITLY set path where it should be rendered :)
  override def relPath = Paths.get("files/simple/my-text.txt")

  override def render = {
    val loremIpsum =
      """|Lorem ipsum dolor sit amet, consectetur adipiscing elit,
         |sed do eiusmod tempor incididunt ut labore et dolore magna aliqua...
         |""".stripMargin
    // this is pure Scala code, you can do anything here
    val myList = for (i <- 1 to 10) yield {
      s"$i. line"
    }
    val result = loremIpsum + myList.mkString("\n")
    result
  }

}
