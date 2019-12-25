package examples.panel

import scalatags.Text.all._
import examples.Imports._
import Panel._

object PanelExample extends StaticPage {

  override def pageSettings =
    super.pageSettings.withTitle("Panels")

  override def pageContent = div(
    br,
    panel(
      panelType = Type.Primary,
      body = "header + body + footer",
      header = Some("Panel header"),
      footer = Some("Panel footer")
    ),
    hr,
    panel(
      panelType = Type.Warning,
      body = "header + body",
      header = Some("Panel header")
    ),
    hr,
    panel(
      panelType = Type.Success,
      body = "body + footer",
      footer = Some("Panel footer")
    )
  )

}
