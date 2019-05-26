package examples.form

import scalatags.Text.all._
import examples.Imports._

object formComponents extends Form {
  //override def formType = Form.Type.Horizontal()
}

object FormExample extends StaticPage with Grid {
  import formComponents._

  override def pageSettings =
    super.pageSettings.withTitle("Form example")

  override def pageContent = frag(
    form(name := "hepek-examples-netlify", method := "POST", attr("netlify").empty)(
      row(
        third(),
        third(
          inputText(id := "txt1")("name", "Name"),
          inputText(id := "txt2", disabled)("disabled", "Disabled"),
          inputEmail(id := "em1", multiple)("email", "Email"),
          inputTel(id := "ph1")("phone", "Phone"),
          inputPassword(id := "pwd1")("pwd", "Password"),
          inputUrl(id := "url1")("url", "URL"),
          // date & time
          inputTime(id := "time1")("time", "Time"),
          inputWeek(id := "wk1")("weel", "Week"),
          inputMonth(id := "mnt1")("month", "Month"),
          inputDate(id := "date1", min := "1900-01-01", max := "2000-01-01")(
            "date",
            "Date of birth"
          ),
          inputDateTimeLocal(id := "date2", min := "2000-01-01T00:00", max := "2020-01-01T00:00")(
            "datetime",
            "Date and time"
          ),
          // other
          inputNumber(id := "num1", min := "1", step := "5")("num", "Number"),
          inputRange(id := "rng1", min := "10", step := "2", max := "50")("range", "Range"),
          inputFile(id := "f1", accept := "image/*")("file", "File"),
          inputColor(id := "boja")("color", "Color"),
          inputCheckbox(id := "m1", checked)("checkbox", "Man?"),
          inputSubmit(value := "CLICK ME!")("Submit"),
          inputButton(value := "Click this!", cls := "btn-warning")("Button"),
          inputReset()("Reset examples.form"),
          inputHidden()("abcHidden")
        ),
        third()
      )
    )
  )
}
