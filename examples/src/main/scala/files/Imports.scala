package files

import ba.sake.hepek.bootstrap5.statik.BootstrapStaticBundle

//import ba.sake.hepek.bulma.statik.BulmaStaticBundle
// try to use a different bundle ^^^

object Imports {

  val Bundle = locally {
    val b = BootstrapStaticBundle.default
    import b.*

    val customGrid = Grid.withScreenRatios(
      // random layout, just for demo
      Grid.screenRatios
        .withLg(Ratios.default.withSingle(1, 4, 1).withHalf(5, 7).withThird(4, 3, 5))
        .withMd(Ratios.default.withHalf(8, 4).withThird(6, 4, 2))
        .withSm(None) // stack ("normal" behavior) on small
        .withXs(None) // and extra-small screens
    )

    val customForm = Form.withType(Form.Companion.Type.Horizontal(1, 3))

    b.withGrid(customGrid).withForm(customForm)
  }

}
