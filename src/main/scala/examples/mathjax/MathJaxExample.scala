package examples.mathjax

import scalatags.Text.all._
import examples.Imports._
import ba.sake.hepek.html.component.BasicComponents
import ba.sake.hepek.mathjax.MathjaxDependencies

object MathJaxExample extends StaticPage with MathjaxDependencies with BasicComponents {

  override def pageSettings =
    super.pageSettings
      .withTitle("MathJax")

  override def pageContent = frag(
    """
      Probably easiest syntax for MathJax is [AsciiMath](http://asciimath.org)
        (but it also supports Latex, MathML etc.).
         
      AsciiMath is written inside backticks, like this:
        `sum_(i=1)^n i^3=((n(n+1))/2)^2`
        """.md,
    pre("`sum_(i=1)^n i^3=((n(n+1))/2)^2`"),
    p("Result is:  `sum_(i=1)^n i^3=((n(n+1))/2)^2`"),
    """
      ---
      But backticks don't work well with Markdown, see this: 
        `sum_(i=1)^n i^3=((n(n+1))/2)^2`  :'(
      
      So we add "forwardticks" (acute accent) in JS config and then use them in Markdown: 
        ´sum_(i=1)^n i^3=((n(n+1))/2)^2´  :p
    """.md
  )

  override def scriptsInline =
    super.scriptsInline ++
      List(s"""
        MathJax.Hub.Config({
            asciimath2jax: {
                delimiters: [['`','`'], ['´','´']]
            }
        });
      """)

}
