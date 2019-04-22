package examples.grid

import scalatags.Text.all._
import ba.sake.hepek.implicits._
import examples.Imports._

object grid extends Grid {

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

object GridExample extends StatikPage {
  import grid._

  override def pageSettings =
    super.pageSettings.withTitle("BS grid")

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
