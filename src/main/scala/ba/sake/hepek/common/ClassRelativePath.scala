package ba.sake.hepek.common

trait ClassRelativePath extends RawRelativePath {

  def extension: String = "html"

  override def fileName: String = {
    this.getClass.getSimpleName.replaceAll("\\$", "") + "." + extension
  }

}