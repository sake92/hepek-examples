
package ba.sake.hepek.example2

import templates.Main
import ba.sake.hepek.example2.images.Image

/**
 * This is the index.html page.
 */
object Index extends Main {

  override def title = "Home Page"

  override def content: String = {
    s"""
      |<h1>Welcome to my Home page!</h1>
      |<img src="${relTo(Image.bosna)}" class="img-rounded" alt="${Image.bosna.relPath.getName}">
    """.stripMargin
  }

}
