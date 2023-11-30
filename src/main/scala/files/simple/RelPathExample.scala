package files.simple

import java.nio.file.Paths
import ba.sake.hepek.core.Renderable

object RelPathExample extends Renderable {

  // we EXPLICITLY set path where it should be rendered :)
  override def relPath = Paths.get("files/simple/relpaths.txt")

  override def render = {

    val pathToTextFile = relTo(TextFile)
    val pathToJSONFile = relTo(JsonFile)
    s"""|Relative path from here to TextFile is $pathToTextFile,
        |and path to JsonFile is $pathToJSONFile.
        |
        |Neat!
        |""".stripMargin
  }

}
