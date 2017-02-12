package ba.sake.hepek.example2.images

import ba.sake.hepek.core.RelativePath
import ba.sake.hepek.common.RawRelativePath

/** Represents an image resource in /images folder */
class ImageResource(fullName: String) extends RawRelativePath {
  override val fileName = fullName
}

object ImageResource {
  def apply(fullName: String): RelativePath = new ImageResource(fullName)
}

object Image {
  val bosna = ImageResource("bosna.png")
}
