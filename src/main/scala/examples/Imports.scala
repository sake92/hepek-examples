package examples

import ba.sake.hepek.bootstrap3.statik.BootstrapStaticBundle
import ba.sake.hepek.bulma.statik.BulmaStaticBundle
import ba.sake.hepek.w3css.statik.W3CssStaticBundle

// try to use a different StaticBundle :)

object Imports {

  private val baseBundle = BootstrapStaticBundle()
  import baseBundle._

  private val customGrid = Grid.withScreenRatios(
    Grid.screenRatios
      .withLg(Ratios().withSingle(1, 4, 1))
      .withMd(Ratios().withSingle(1, 4, 1))
      .withSm(None) // stack on small
      .withXs(None) // and extra-small screens
  )

  val Bundle = baseBundle.withGrid(customGrid)

}
