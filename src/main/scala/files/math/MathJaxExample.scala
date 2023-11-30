package files.math

import scalatags.Text.all._
import files.Imports.Bundle._
import ba.sake.hepek.mathjax.MathjaxDependencies

object MathJaxExample extends StaticPage with MathjaxDependencies {

  override def pageSettings =
    super.pageSettings.withTitle("MathJax")

  val formula = """sum_(i=1)^n i^3=((n(n+1))/2)^2"""

  override def pageContent = frag(
    """
    Probably easiest syntax for MathJax is [AsciiMath](http://asciimath.org)
      (but it also supports Latex, MathML etc.).
        
    AsciiMath is written inside forward-ticks, like this:
    """.md,
    pre(s"´$formula´"),
    p(s"Result is: ´$formula´")
  )

  override def scriptsInline =
    super.scriptsInline ++
      List(s"""
        MathJax.Hub.Config({
            asciimath2jax: {
                delimiters: [['´','´']]
            }
        });
      """)

}
