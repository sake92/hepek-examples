package examples.bootstrap

import scalatags.Text.all._
import ba.sake.hepek.bootstrap3.component._
import ba.sake.hepek.bootstrap3.statik.BootstrapStaticPage

object BootstrapGridExample extends BootstrapStaticPage {
  import MyGrid._

  override def pageSettings =
    super.pageSettings
      .withTitle("BS grid")

  override def pageContent = frag(
    b("Resize browser window to see effect!"),
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

  val lgHalfRatio  = Ratio(5, 7)    // 5:7
  val lgThirdRatio = Ratio(4, 3, 5) // 4:3:5

  val mdHalfRatio  = Ratio(8, 4)    // 8:4
  val mdThirdRatio = Ratio(6, 4, 2) // 6:4:2

  // stack ("normal" behavior) on small and extra-small screens
  override def screenRatios =
    super.screenRatios
      .withLg(Ratios(lgHalfRatio, lgThirdRatio))
      .withMd(Ratios(mdHalfRatio, mdThirdRatio))
      .withSm(None)
      .withXs(None)

}
