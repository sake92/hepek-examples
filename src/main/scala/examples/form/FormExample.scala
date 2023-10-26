package examples.form

import scalatags.Text.all._
import examples.Imports.Bundle._, Grid._, Classes._
import ba.sake.hepek.fontawesome5.FADependencies
import ba.sake.hepek.fontawesome5.FA

object FormExample extends StaticPage with FADependencies {

  val customForm = Form.copy(formType = Form.Companion.Type.Horizontal())
  import customForm._

  override def pageSettings =
    super.pageSettings.withTitle("Form example")

  override def pageContent = frag(
    form(name := "hepek-examples-netlify", method := "POST", attr("netlify").empty)(
      row(
        formFieldset("Textual inputs")(
          inputText()("name", "Name"),
          inputText()(_name = "surname", _label = "Surname"), // you can use named args!
          inputText(disabled)("disabled", "Disabled"),
          inputText()(
            "customized",
            "Customized",
            _transform = field =>
              div(cls := "input-group")(
                span(cls := "input-group-addon")(FA.user()),
                field
              )
          ),
          inputTextArea(attr("minlength") := "5")("textArea", "Free text"),
          inputEmail(multiple)("email", "Email"),
          inputTel()("phone", "Phone"),
          inputPassword()("pwd", "Password"),
          inputUrl()("url", "URL")
        ),
        formFieldset("Checkbox inputs")(
          inputCheckbox(checked)("checkbox", "Man?"),
          hr,
          inputCheckboxesSimple(
            "progLangs",
            Seq(("scala", "Scala"), ("java", "Java")),
            _label = "Favorite languages",
            _isInline = false
          )
        ),
        formFieldset("Radio inputs")(
          inputRadioSimple(
            "favoriteSuperHero",
            Seq(("batman", "Batman"), ("superman", "Superman")),
            _label = "Super hero",
            _checkedValue = "superman",
            _isInline = false
          )
        ),
        formFieldset("Select inputs")(
          inputSelect()(
            "cars",
            Seq(("volvo", "Volvo", Nil), ("bmw", "BMW", Seq(selected))),
            _label = "Cars"
          )
        ),
        formFieldset("Date/time inputs")(
          inputTime()("time", "Time"),
          inputWeek()("weel", "Week"),
          inputMonth()("month", "Month"),
          inputDate(min := "1900-01-01", max := "2000-01-01")(
            "date",
            "Date of birth"
          ),
          inputDateTimeLocal(
            min := "2000-01-01T00:00",
            max := "2020-01-01T00:00"
          )(
            "datetime",
            "Date and time"
          )
        ),
        formFieldset("Numeric inputs")(
          inputNumber(min := "1", step := "5")("num", "Number"),
          inputRange(min := "10", step := "2", max := "50", value := "44")("range", "Range")
        ),
        formFieldset("Misc inputs")(
          inputFile(accept := "image/*")("file", "File"),
          inputColor()("color", "Color")
        ),
        formFieldset("Button inputs")(
          inputSubmit(btnSizeLg)("Submit (large button)"),
          inputButton(btnWarning, btnSizeSm)(
            frag(FA.times(), " Remove  (small button)")
          ),
          inputReset(btnDanger, btnWidthFull)("Reset (danger button)"),
          inputHidden()("abcHidden")
        )
      )
    )
  )
}
