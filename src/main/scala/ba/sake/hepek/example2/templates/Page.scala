package ba.sake.hepek.example2.templates

import ba.sake.hepek.core._
import ba.sake.hepek.example2.js.JS
import ba.sake.hepek.common.ClassRelativePath

trait Page extends ClassRelativePath with Renderable {

  def title: String

  def body: String

  def relTo(other: RelativePath): String = {
    this.relPath.toPath.getParent.relativize(other.relPath.toPath).toString
  }

  override def render: String = {
    s"""
      |<!DOCTYPE html>
      |<html lang="en">
      |  <head>
      |    <meta charset="utf-8">
      |    <meta name="viewport" content="width=device-width, initial-scale=1">
      |    <title>$title</title>
      |    <!-- Include Bootstrap CSS -->
      |    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
      |  </head>
      |  <body>
      |    <div class="container-fluid">
      |      $body
      |    </div>
      |    <!-- Include Bootstrap & jQuery libs -->
      |    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
      |    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
      |    <script src="${relTo(JS.myJs)}"></script>
      |  </body>
      </html>
    """.stripMargin
  }

}