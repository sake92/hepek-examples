package files

import ba.sake.hepek.bootstrap3.statik.BootstrapStaticBundle
//import ba.sake.hepek.bulma.statik.BulmaStaticBundle

// try to use a different bundle ^^^

object Imports {

  val Bundle = locally {
    val b = BootstrapStaticBundle.default
    import b._
    val customGrid = Grid.withScreenRatios(
      b.Grid.screenRatios
        .withLg(Ratios.default.withSingle(1, 4, 1))
        .withMd(Ratios.default.withSingle(1, 4, 1))
        .withSm(None) // stack on small
        .withXs(None) // stack on extra-small screens
    )
    b.withGrid(customGrid)
  }

}
