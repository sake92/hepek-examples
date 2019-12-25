package examples.form

import scalatags.Text.all._
import examples.Imports._
import examples.grid1._

object formComponents extends Form {
  //override def formType = Form.Type.Horizontal()
}

object FormExample extends StaticPage {
  import formComponents._
  import classes._

  override def pageSettings =
    super.pageSettings.withTitle("Form example")

  override def pageContent = frag(
    form(name := "hepek-examples-netlify", method := "POST", attr("netlify").empty)(
      row(
        formFieldset("Textual inputs")(
          inputText(id := "txt1")("name", "Name"),
          inputText(id := "txt2", disabled)("disabled", "Disabled"),
          inputText(id := "txt3")(
            "customized",
            "Customized",
            _transform = field =>
              div(cls := "input-group")(
                span(cls := "input-group-addon")(i(cls := "glyphicon glyphicon-user")),
                field
              )
          ),
          inputTextArea(id := "ta1", attr("minlength") := "5")("textArea", "Free text"),
          inputEmail(id := "em1", multiple)("email", "Email"),
          inputTel(id := "ph1")("phone", "Phone"),
          inputPassword(id := "pwd1")("pwd", "Password"),
          inputUrl(id := "url1")("url", "URL")
        ),
        formFieldset("Checkbox inputs")(
          inputCheckbox(id := "m1", checked)("checkbox", "Man?"),
          hr,
          inputCheckboxes(
            "progLangs",
            Seq(("scala", "Scala", Nil), ("java", "Java", Nil)),
            _label = "Favorite languages",
            _isInline = false
          )
        ),
        formFieldset("Radio inputs")(
          inputRadio(
            "favoriteSuperHero",
            Seq(("batman", "Batman", Nil), ("superman", "Superman", Nil)),
            _label = "Super hero",
            _checkedValue = "superman",
            _isInline = false
          )
        ),
        formFieldset("Select inputs")(
          inputSelect(id := "selectCar")(
            "cars",
            Seq(("volvo", "Volvo", Nil), ("bmw", "BMW", Seq(selected))),
            _label = "Cars"
          ),
          inputSelectGrouped(multiple, size := "7")(
            "animals",
            Seq(
              "Cats" -> Seq(
                ("bengal", "Bengal", Nil),
                ("persian", "Persian", Seq(selected))
              ),
              "Dogs" -> Seq(
                ("goldenRetriever", "Golden retriever", Seq(selected)),
                ("husky", "Husky", Nil)
              )
            ),
            _label = "Animals"
          )
        ),
        formFieldset("Date/time inputs")(
          inputTime(id := "time1")("time", "Time"),
          inputWeek(id := "wk1")("weel", "Week"),
          inputMonth(id := "mnt1")("month", "Month"),
          inputDate(id := "date1", min := "1900-01-01", max := "2000-01-01")(
            "date",
            "Date of birth"
          ),
          inputDateTimeLocal(
            id := "dateTime1",
            min := "2000-01-01T00:00",
            max := "2020-01-01T00:00"
          )(
            "datetime",
            "Date and time"
          )
        ),
        formFieldset("Numeric inputs")(
          inputNumber(id := "num1", min := "1", step := "5")("num", "Number"),
          inputRange(id := "rng1", min := "10", step := "2", max := "50")("range", "Range")
        ),
        formFieldset("Misc inputs")(
          inputFile(id := "f1", accept := "image/*")("file", "File"),
          inputColor(id := "kolor1")("color", "Color")
        ),
        formFieldset("Button inputs")(
          inputSubmit(btnSizeLg)("Submit (large button)"),
          inputButton(btnWarning, btnSizeSm)(
            "btnRemove",
            frag(span(cls := "glyphicon glyphicon-remove"), " Remove  (small button)")
          ),
          inputReset(btnDanger, btnWidthFull)("Reset (danger button)"),
          inputHidden()("abcHidden")
        )
      )
    )
  )
}
