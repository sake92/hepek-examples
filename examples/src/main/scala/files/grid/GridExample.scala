package files.grid

import files.Bundle.*, Tags.*

object GridExample extends StaticPage {

  override def navbar = None

  override def pageSettings =
    super.pageSettings.withTitle("Grid")

  override def pageContent = frag(
    """
    ---
    ### Resize browser window to see effect!
    ---
    """.md,
    Grid.row(
      "Centered row, single column"
    ),
    hr,
    Grid.row(
      Grid.half("First half"),
      Grid.half("Second half")
    ),
    hr,
    Grid.row(
      Grid.third("First third"),
      Grid.third("Second third"),
      Grid.third("Third third")
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
