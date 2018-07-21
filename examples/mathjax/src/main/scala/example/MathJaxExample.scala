package example

import scalatags.Text.all._
import ba.sake.hepek.html.structure.SiteSettings
import ba.sake.hepek.html.component.BasicComponents._
import ba.sake.hepek.bootstrap3.statik.BootstrapStaticPage
import ba.sake.hepek.mathjax.MathjaxDependencies

object MathJaxExample extends BootstrapStaticPage with MathjaxDependencies {

  override def pageTitle           = "MathJax"
  override def siteSettings        = SiteSettings("MathJax", MathJaxExample)
  override def withBootstrapNavbar = false

  override def pageContent = frag(
    p(
      "Probably easiest syntax for MathJax is ",
      hyperlink("http://asciimath.org/", true)("AsciiMath"),
      " (but it also supports Latex, MathML etc.)"
    ),
    p("AsciiMath is written inside backticks, like this:"),
    pre("`sum_(i=1)^n i^3=((n(n+1))/2)^2`"),
    p("Result is:  `sum_(i=1)^n i^3=((n(n+1))/2)^2`"),
    md("""
      ---
      But backticks don't work well with Markdown, see this: 
        `sum_(i=1)^n i^3=((n(n+1))/2)^2`  :'(
      
      So we add "forwardticks" (acute accent) in JS config and then use them in Markdown: 
        ´sum_(i=1)^n i^3=((n(n+1))/2)^2´  :p
    """)
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
