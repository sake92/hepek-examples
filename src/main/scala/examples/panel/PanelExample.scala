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
      header = Some("Panel header"),
      body = "header + body + footer",
      footer = Some("Panel footer")
    ),
    hr,
    Panel.panel(
      panelType = Panel.Companion.Type.Warning,
      header = Some("Panel header"),
      body = "header + body"
    ),
    hr,
    Panel.panel(
      panelType = Panel.Companion.Type.Success,
      body = "body + footer",
      footer = Some("Panel footer")
    )
  )

}
