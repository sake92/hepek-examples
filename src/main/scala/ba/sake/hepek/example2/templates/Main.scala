package ba.sake.hepek.example2.templates

import ba.sake.hepek.example2.pages._
import ba.sake.hepek.example2.Index

trait Main extends Page {

  def content: String

  /* Every page will have this sidebar rendered */
  def allPages: List[Page] = List(Index, Page1, Page2)

  def sidebar: String = {
    val pageLiTags = allPages.map { p =>
      val activeClass = if (p.relPath == this.relPath) "class='active'" else ""
      val hrefToPage = relTo(p)
      s"""<li $activeClass><a href="$hrefToPage">${p.title}</a></li>"""
    }.mkString("\n")

    s"""
      |<ul class="nav nav-pills nav-stacked">
      |  $pageLiTags
      |</ul>
    """.stripMargin
  }

  override def body: String = {
    s"""
      |<div class="row">
      |  <div class="col-xs-3">$sidebar</div>
      |  <div class="col-xs-7">$content</div>
      |</div>
    """.stripMargin
  }

}