package examples.math

import scalatags.Text.all._
import examples.Imports._
import ba.sake.hepek.katex.KatexDependencies

object KatexExample extends StaticPage with KatexDependencies {

  override def pageSettings =
    super.pageSettings.withTitle("KaTeX")

  val formula = """\cfrac{a}{1 + \cfrac{1}{b}}"""

  override def pageContent = frag(
    s"""
    KaTeX is written in Latex format.
         
    You write it inside forward-ticks, like this: 
    """.md,
    pre(s"´$formula´"),
    s"""
    Result is:  ´$formula´

    [Click here](https://katex.org/docs/supported.html) for more syntax docs.
    """.md
  )

}
