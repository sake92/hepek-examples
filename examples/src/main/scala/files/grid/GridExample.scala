package files.grid

import scalatags.Text.all._
import files.Imports.Bundle._

object GridExample extends StaticPage {

  val grid = Grid.withScreenRatios(
    Grid.screenRatios
      .withLg(Ratios.default.withSingle(1, 4, 1).withHalf(5, 7).withThird(4, 3, 5))
      .withMd(Ratios.default.withHalf(8, 4).withThird(6, 4, 2))
      .withSm(None) // stack ("normal" behavior) on small
      .withXs(None) // and extra-small screens
  )
  import grid._

  override def pageSettings =
    super.pageSettings.withTitle("Grid")

  override def pageContent = frag(
    """
    ---
    ### Resize browser window to see effect!
    ---
    """.md,
    row(
      "Centered row, single column"
    ),
    hr,
    row(
      half("First half"),
      half("Second half")
    ),
    hr,
    row(
      third("First third"),
      third("Second third"),
      third("Third third")
    )
  )

  // this is just to help visualize columns
  override def stylesInline = super.stylesInline ++ List(
    """
      div[class^='col'] {
        border: 1px solid black; 
      }
    """
  )

}
