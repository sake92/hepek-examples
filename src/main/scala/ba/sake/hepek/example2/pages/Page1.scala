package ba.sake.hepek.example2.pages

import ba.sake.hepek.example2.templates.Main
import ba.sake.hepek.example2.Index

object Page1 extends Main {

  override def title = "Page 1"

  override def content: String = {
    s"""    
      |<h1>$title</h1>      
      |This is my First page :) <br>
    """.stripMargin
  }

}