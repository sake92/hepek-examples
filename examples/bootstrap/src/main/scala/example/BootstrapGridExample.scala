package example

import scalatags.Text.all._
import ba.sake.hepek.html.structure.SiteSettings
import ba.sake.hepek.bootstrap3.statik.BootstrapStaticPage
import ba.sake.hepek.bootstrap3.component._

object BootstrapGridExample extends BootstrapStaticPage {
  import MyGrid._

  override def pageTitle    = " BS grid "
  override def siteSettings = SiteSettings("my.site", BootstrapGridExample)

  override def pageContent = frag(
    row("Row with plain content, no hepek stuff..."),
    hr,
    row(
      half1("First half"),
      half2("Second half")
    ),
    hr,
    row(
      third1("First third"),
      third2("Second third"),
      third3("Third third")
    )
  )

  // this is just to help visualize columns
  override def stylesInline = super.stylesInline ++ List(
    """
      div[class^='col'] { 
        border:1px solid black; 
      }
    """
  )

}

object MyGrid extends BootstrapGridComponents {
  import ba.sake.hepek.html.component.GridComponents._
  // you can use defaults
  // here we see how you can tweak this grid stuff

  val lgHalfRatio  = Ratio(List(5, 7))    // 5:7
  val lgThirdRatio = Ratio(List(4, 3, 5)) // 4:3:5

  val mdHalfRatio  = Ratio(List(8, 4))    // 8:4
  val mdThirdRatio = Ratio(List(6, 4, 2)) // 6:4:2

  // stack ("normal" behavior) on small and extra-small screens
  override def screenRatios = super.screenRatios.copy(
    lg = Ratios(lgHalfRatio, lgThirdRatio),
    md = Option(Ratios(mdHalfRatio, mdThirdRatio)),
    sm = None,
    xs = None
  )

}
