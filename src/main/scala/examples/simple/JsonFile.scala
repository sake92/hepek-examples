package examples.simple

import java.nio.file.Paths
import java.util.UUID
import ba.sake.hepek.core.Renderable

object JsonFile extends Renderable {

  // we EXPLICITLY set path where it should be rendered :)
  override def relPath = Paths.get("examples/simple/json/my-json.json")

  override def render = {

    val peopleJSON = for (p <- Person.people) yield {
      s"""|{
          |  "id":    "${p.id}",
          |  "name":  "${p.name}",
          |  "age":    ${p.age}
          |}""".stripMargin
    }
    // you get the idea... :)
    s"""|{
        |  "people": [
        |    ${peopleJSON.mkString(",")}
        |  ]
        |}""".stripMargin
  }

}

case class Person(id: UUID, name: String, age: Int)

object Person {

  val people = List(
    Person(UUID.randomUUID(), "Sakib", 25),
    Person(UUID.randomUUID(), "Mirsad", 26),
    Person(UUID.randomUUID(), "Amer", 26),
    Person(UUID.randomUUID(), "Muris", 35)
  )
}
