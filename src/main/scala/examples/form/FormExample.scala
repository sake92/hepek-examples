package examples.form

import scalatags.Text.all._
import examples.Imports._

object formComponents extends Form {
  import ba.sake.hepek.bootstrap3.component.BootstrapFormComponents._
  //override def bootstrapFormType: Type = Type.Horizontal()

  //import ba.sake.hepek.pure.component.PureFormComponents._
  //override def pureFormType: Type = Type.Horizontal
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
          inputText("Name", name := "name", id := "txt1"),
          inputText("Disabled", name := "disabled", id := "txt2", disabled),
          inputEmail("Email", name := "email", id := "em1", multiple),
          inputTel("Phone", name := "phone", id := "ph1"),
          inputPassword("Password", name := "pwd", id := "pwd1"),
          inputUrl("URL", name := "url", id := "url1"),
          /////////////
          inputTime("Time", name := "time", id := "time1"),
          inputWeek("Week", name := "weel", id := "wk1"),
          inputMonth("Month", name := "month", id := "mnt1"),
          inputDate(
            "Date of birth",
            name := "date",
            id := "date1",
            min := "1900-01-01",
            max := "2000-01-01"
          ),
          inputDateTimeLocal(
            "Date and time",
            id := "date2",
            name := "datetime",
            min := "2000-01-01T00:00",
            max := "2020-01-01T00:00"
          ),
          //////////////////////
          inputNumber("Number", min := "1", step := "5", name := "num", id := "num1"),
          inputRange("Range", min := "10", step := "2", max := "50", name := "range", id := "rng1"),
          inputFile("File", name := "file", id := "f1", accept := "image/*"),
          inputColor("Color", name := "color", id := "boja"),
          inputCheckbox("Man?", name := "checkbox", id := "m1", checked),
          inputSubmit("Submit", value := "CLICK ME!"),
          inputButton("Button", value := "Click this!", cls := "btn-warning"),
          inputReset("Reset examples.form"),
          inputHidden(name := "abcHidden")
        ),
        third()
      )
    )
  )
}
