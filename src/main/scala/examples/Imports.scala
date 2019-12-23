package examples

import ba.sake.hepek.html.component.BasicComponents
import ba.sake.hepek.bootstrap3.statik.BootstrapStaticBundle
import ba.sake.hepek.bulma.statik.BulmaStaticBundle
import ba.sake.hepek.w3css.statik.W3CssStaticBundle

// try to use different Bundle :)
object Imports extends BootstrapStaticBundle with BasicComponents
//object Imports extends W3CssStaticBundle with BasicComponents
//object Imports extends BulmaStaticBundle with BasicComponents

import Imports._

object grid1 extends Grid { // default grid to import globally..
  override def screenRatios =
    super.screenRatios.withAll(Ratios().withSingle(1, 3, 1))
}
