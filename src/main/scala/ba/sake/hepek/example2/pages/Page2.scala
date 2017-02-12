package ba.sake.hepek.example2.pages

import ba.sake.hepek.example2.templates.Main
import ba.sake.hepek.example2.Index

object Page2 extends Main {

  override def title = "Page2"

  override def content: String = {
    s"""
      |<h1>$title</h1>
      |This is my Second Page, duh! :D<br>
      |Go hard or <a href='${relTo(Index)}'>go Home</a>
    """.stripMargin
  }

}