package examples.simple

import java.nio.file.Paths
import java.util.UUID
import ba.sake.tupson.*
import ba.sake.hepek.core.Renderable

object JsonFile extends Renderable {

  // explicitly set path where to write
  override def relPath = Paths.get("examples/simple/json/my-json.json")

  override def render =
    s"""|{
        |  "people": ${Person.people.toJson}
        |}""".stripMargin

}

case class Person(id: UUID, name: String, age: Int) derives JsonRW

object Person {

  val people = List(
    Person(UUID.randomUUID(), "Sakib", 25),
    Person(UUID.randomUUID(), "Mirsad", 26),
    Person(UUID.randomUUID(), "Amer", 26),
    Person(UUID.randomUUID(), "Muris", 35)
  )
}
