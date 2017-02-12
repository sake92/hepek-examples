package ba.sake.hepek.example1

import ba.sake.hepek.common.ClassRelativePath
import ba.sake.hepek.core.Renderable

object MyText extends ClassRelativePath with Renderable {

  override def extension: String = "txt"

  override def render: String = {
    val loremIpsum: String = """
      Lorem ipsum dolor sit amet, consectetur adipiscing elit,
      sed do eiusmod tempor incididunt ut labore et dolore magna aliqua...
    """
    // this is pure Scala code, you can do anything here
    val myList = for (i <- 1 to 10) yield {
      s"$i. line"
    }
    loremIpsum + myList.mkString("\n")
  }
}