package examples.panel

import scalatags.Text.all._
import examples.Imports._

object PanelExample extends StaticPage {

  val myPanel = Panel

  override def pageSettings =
    super.pageSettings.withTitle("Panels")

  override def pageContent = div(
    br,
    myPanel.panel(
      panelType = myPanel.Companion.Type.Primary,
      body = "header + body + footer",
      header = Some("Panel header"),
      footer = Some("Panel footer")
    ),
    hr,
    myPanel.panel(
      panelType = myPanel.Companion.Type.Warning,
      body = "header + body",
      header = Some("Panel header")
    ),
    hr,
    myPanel.panel(
      panelType = myPanel.Companion.Type.Success,
      body = "body + footer",
      footer = Some("Panel footer")
    )
  )

}
