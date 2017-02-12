package ba.sake.hepek.common

import java.io.File
import ba.sake.hepek.core.RelativePath

trait RawRelativePath extends RelativePath {

  /** Simple name of the rendered file. */
  def fileName: String

  /** Path of resource relative to `sbt-hepek:hepekTarget` folder.   */
  override def relPath: File = {
    val packagePath = if (this.getClass.getPackage == null) { // don't touch this! needed for Path#relativize later..
      "dummy-root"
    } else {
      this.getClass.getPackage.getName.replaceAll("\\.", "/")
    }
    val p = packagePath + "/" + fileName
    new File(p.toLowerCase)
  }
}