package lib

import ba.sake.hepek.core.RelativePath
import ba.sake.hepek.common.RawRelativePath

/** Represents an image resource in /images folder */
class WebJar(fullName: String) extends RawRelativePath {
  override val fileName = fullName
}

object WebJar {
  def apply(fullName: String): RelativePath = new WebJar(fullName)
}

object WebJars {
  object js {
    val jQuery = WebJar("jquery/jquery.min.js")
    val bootstrap = WebJar("bootstrap/js/bootstrap.min.js")
  }
  object css {
    val bootstrap = WebJar("bootstrap/css/bootstrap.min.css")
  }
}