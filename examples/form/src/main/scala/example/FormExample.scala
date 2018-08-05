package docs

import scalatags.Text.all._
import ba.sake.hepek.bootstrap3.statik.BootstrapStaticPage
import ba.sake.hepek.pure.statik.PureStaticPage
import ba.sake.hepek.html.structure.SiteSettings

/* To try PureCSS instead of Bootstrap3, switch to PureStaticPage and PrFormComponents */

object FormExample extends BootstrapStaticPage {
//object FormExample extends PureStaticPage {
  import formComponents._

  override def pageTitle    = "Form example"
  override def siteSettings = SiteSettings("my.site", this)

  override def pageContent = frag(
    formm("http://localhost:8080")(
      row(
        third1(),
        third2(
          inputText("Name", id := "txt1"),
          inputText("Disabled", id := "txt2", disabled),
          inputEmail("Email", id := "em1", multiple),
          inputTel("Phone", id := "ph1"),
          inputPassword("Password", id := "pwd1"),
          inputUrl("URL", id := "url1"),
          /////////////
          inputTime("Time", id := "time1"),
          inputWeek("Week", id := "wk1"),
          inputMonth("Month", id := "mnt1"),
          inputDate("Date of birth",
                    id := "date1",
                    min := "1900-01-01",
                    max := "2000-01-01"),
          inputDateTimeLocal("Date and time",
                             id := "date2",
                             min := "2000-01-01T00:00",
                             max := "2020-01-01T00:00"),
          //////////////////////
          inputNumber("Number", min := "1", step := "5", id := "num1"),
          inputRange("Range",
                     min := "10",
                     step := "2",
                     max := "50",
                     id := "rng1"),
          inputFile("File", id := "f1", accept := "image/*"),
          inputColor("Color", id := "boja"),
          inputCheckbox("Man?", id := "m1", checked),
          inputSubmit("Submit", value := "CLICK ME!", id := "sub1"),
          inputButton("Button",
                      value := "Click this!",
                      id := "btn1",
                      cls := "btn-warning"),
          inputReset("Reset form"),
          inputHidden(name := "abcHidden")
        ),
        third3()
      )
    )
  )
}

// this should be in utils, for easier import from different pages! :)
// use one of (currently) 2 form support frameworks: bootstrap3 or pure
object formComponents extends BsFormComponents
//object formComponents extends PrFormComponents

import ba.sake.hepek.bootstrap3.component._

trait BsFormComponents
    extends BootstrapFormComponents
    with BootstrapGridComponents {
  import BootstrapFormComponents._
  override def bootstrapFormType: Type = Type.Horizontal()
}

import ba.sake.hepek.pure.component._

trait PrFormComponents extends PureFormComponents with PureGridComponents {
  import PureFormComponents._
  override def pureFormType: Type = Type.Horizontal
}
