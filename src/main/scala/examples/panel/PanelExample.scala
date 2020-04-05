package examples.panel

import scalatags.Text.all._
import examples.Imports.Bundle._

object PanelExample extends StaticPage {

  override def pageSettings =
    super.pageSettings.withTitle("Panels")

  override def pageContent = div(
    br,
    Panel.panel(
      panelType = Panel.Companion.Type.Primary,
      body = "header + body + footer",
      header = Some("Panel header"),
      footer = Some("Panel footer")
    ),
    hr,
    Panel.panel(
      panelType = Panel.Companion.Type.Warning,
      body = "header + body",
      header = Some("Panel header")
    ),
    hr,
    Panel.panel(
      panelType = Panel.Companion.Type.Success,
      body = "body + footer",
      footer = Some("Panel footer")
    )
  )

}
