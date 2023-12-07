package files.math

import ba.sake.hepek.mathjax.MathjaxDependencies
import files.Bundle.*, Tags.*

object MathJaxExample extends StaticPage with MathjaxDependencies {

  override def navbar = None

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
