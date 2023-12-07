package files.form

import ba.sake.hepek.fontawesome5.FADependencies
import ba.sake.hepek.fontawesome5.FA
import files.Imports.Bundle.*, Tags.*, Classes.*

object FormExample extends StaticPage with FADependencies {

  override def navbar = None

  override def pageSettings =
    super.pageSettings.withTitle("Form example")

  override def pageContent = frag(
    Form.form(name := "hepek-examples-netlify", method := "POST", attr("netlify").empty)(
      Grid.row(
        Form.formFieldset("Textual inputs")(
          Form.inputText(autofocus)("name", "Name"),
          Form.inputText()(_name = "surname", _label = "Surname"), // you can use named args!
          Form.inputText(disabled)("disabled", "Disabled"),
          Form.inputText()(
            "customized",
            "Customized",
            _transform = field =>
              div(cls := "input-group")(
                span(cls := "input-group-addon")(FA.user()),
                field
              )
          ),
          Form.inputTextArea(attr("minlength") := "5")("textArea", "Free text"),
          Form.inputEmail(multiple)("email", "Email"),
          Form.inputTel()("phone", "Phone"),
          Form.inputPassword()("pwd", "Password"),
          Form.inputUrl()("url", "URL")
        ),
        Form.formFieldset("Checkbox inputs")(
          Form.inputCheckbox(checked)("checkbox", "Man?"),
          hr,
          Form.inputCheckboxesSimple(
            "progLangs",
            Seq(("scala", "Scala"), ("java", "Java")),
            _label = "Favorite languages",
            _isInline = false
          )
        ),
        Form.formFieldset("Radio inputs")(
          Form.inputRadioSimple(
            "favoriteSuperHero",
            Seq(("batman", "Batman"), ("superman", "Superman")),
            _label = "Super hero",
            _checkedValue = "superman",
            _isInline = false
          )
        ),
        Form.formFieldset("Select inputs")(
          Form.inputSelect()(
            "cars",
            Seq(("volvo", "Volvo", Nil), ("bmw", "BMW", Seq(selected))),
            _label = "Cars"
          )
        ),
        Form.formFieldset("Date/time inputs")(
          Form.inputTime()("time", "Time"),
          Form.inputWeek()("weel", "Week"),
          Form.inputMonth()("month", "Month"),
          Form.inputDate(min := "1900-01-01", max := "2000-01-01")("date", "Date of birth"),
          Form.inputDateTimeLocal(min := "2000-01-01T00:00", max := "2020-01-01T00:00")("datetime", "Date and time")
        ),
        Form.formFieldset("Numeric inputs")(
          Form.inputNumber(min := "1", step := "5")("num", "Number"),
          Form.inputRange(min := "10", step := "2", max := "50", value := "44")("range", "Range")
        ),
        Form.formFieldset("Misc inputs")(
          Form.inputFile(accept := "image/*")("file", "File"),
          Form.inputColor()("color", "Color")
        ),
        Form.formFieldset("Button inputs")(
          Form.inputSubmit(btnSizeLg)("Submit (large button)"),
          Form.inputButton(btnWarning, btnSizeSm)(
            frag(FA.times(), " Remove  (small button)")
          ),
          Form.inputReset(btnDanger, btnWidthFull)("Reset (danger button)"),
          Form.inputHidden()("abcHidden")
        )
      )
    )
  )
}
