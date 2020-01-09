package examples

import ba.sake.hepek.html.component.BasicComponents
import ba.sake.hepek.bootstrap3.statik.BootstrapStaticBundle
import ba.sake.hepek.bulma.statik.BulmaStaticBundle
import ba.sake.hepek.w3css.statik.W3CssStaticBundle
import ba.sake.hepek.html.component.GridComponents
import ba.sake.hepek.bootstrap3.statik.BootstrapStaticPage

// try to use different Bundle :)
trait MyBundle extends BootstrapStaticBundle
//trait MyBundle extends W3CssStaticBundle
//trait MyBundle extends BulmaStaticBundle

object Imports extends MyBundle {

  // default grid to import globally..
  val grid1 = Grid.withScreenRatios(
    Grid.screenRatios
      .withLg(Ratios().withSingle(1, 4, 1))
      .withMd(Ratios().withSingle(1, 4, 1))
      .withSm(None) // stack on small
      .withXs(None) // and extra-small screens
  )
}
