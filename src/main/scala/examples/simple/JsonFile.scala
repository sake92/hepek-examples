package examples.simple

import java.nio.file.Paths
import java.util.UUID
import ba.sake.hepek.html.utils.HepekPickler._
import ba.sake.hepek.core.Renderable

object JsonFile extends Renderable {

  // we EXPLICITLY set path where it should be rendered :)
  override def relPath = Paths.get("examples/simple/json/my-json.json")

  override def render =
    s"""|{
        |  "people": ${write(Person.people)}
        |}""".stripMargin

}

case class Person(id: UUID, name: String, age: Int)

object Person {

  implicit val rw: ReadWriter[Person] = macroRW

  val people = List(
    Person(UUID.randomUUID(), "Sakib", 25),
    Person(UUID.randomUUID(), "Mirsad", 26),
    Person(UUID.randomUUID(), "Amer", 26),
    Person(UUID.randomUUID(), "Muris", 35)
  )
}
