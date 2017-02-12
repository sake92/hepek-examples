package ba.sake.hepek.example2.js

import ba.sake.hepek.core.RelativePath
import ba.sake.hepek.common.RawRelativePath

/** Represents an javascript resource in /js folder */
class JSResource(name: String) extends RawRelativePath {
  override val fileName = name + ".js"
}

object JSResource {
  def apply(name: String): RelativePath = new JSResource(name)
}

object JS {
  val myJs = JSResource("myjs")
}