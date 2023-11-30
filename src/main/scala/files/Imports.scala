package files

import ba.sake.hepek.bootstrap3.statik.BootstrapStaticBundle
//import ba.sake.hepek.bulma.statik.BulmaStaticBundle

// try to use a different bundle ^^^

object Imports {

  private val b = BootstrapStaticBundle()
  import b._

  val Bundle = locally {
    val customGrid = Grid.withScreenRatios(
      b.Grid.screenRatios
        .withLg(Ratios().withSingle(1, 4, 1))
        .withMd(Ratios().withSingle(1, 4, 1))
        .withSm(None) // stack on small
        .withXs(None) // and extra-small screens
    )
    b.withGrid(customGrid)
  }

}
