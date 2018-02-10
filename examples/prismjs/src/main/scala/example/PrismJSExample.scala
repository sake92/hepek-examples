package example

import scalatags.Text.all._
import ba.sake.hepek.html.structure.SiteSettings
import ba.sake.hepek.bootstrap3.statik.BootstrapStaticPage
import ba.sake.hepek.prismjs._
import ba.sake.hepek.html.structure.blog.Section

object PrismJSExample extends BootstrapStaticPage with PrismDependencies {

  override def pageTitle    = "code examples"
  override def siteSettings = SiteSettings("my.tutorials", PrismJSExample)

  // invisible characters
  //override def prismTheme = "prism-twilight"

  // invisible characters
  //override def prismShowInvisibles = true

  // language badge
  //override def prismShowLanguage = false

  // copy button
  //override def prismCopyToClipboard = false

  override def pageContent =
    div(cls := "row")(
      div(cls := "col-md-6 col-md-offset-3")(
        sections.map { s =>
          frag(h2(s.name), frag(s.content))
        }
      )
    )

  def sections = List(basicsSection, lineNumsSection, cmdSection, markupSection)

  val basicsSection = Section(
    "Basics",
    div(
      h3("Block of code"),
      chl.java("""
        public class HelloWorld {
            public static void main(String[] args) {
                System.out.println("Hello, Java!"); // a comment
            }
        }
      """),
      h3("Inline code"),
      p(
        "This is ",
        chl.scala.inline("""Some("inline")"""),
        " Scala code."
      ),
      h3("File fetched with AJAX"),
      chl.java.ajax(
        "https://raw.githubusercontent.com/sake92/hepek-core/master/src/main/java/ba/sake/hepek/core/Renderable.java"
      )
    )
  )

  val lineNumsSection = Section(
    "Line numbers",
    div(
      h3("Default"),
      chl.java("""
        public class HelloWorld {
            public static void main(String[] args) {
                System.out.println("Hello, Java!"); // a comment
            }
        }
      """),
      h3("Shifted line numbers (start from -2)"),
      chl.ruby.withLineNumsStart(-2)("""
        def hello(name)
          puts "Hello #{name}"
        end
        hello("Ruby")
      """),
      h3("""Only some lines ("1,5-6"), perfect for tutorials"""),
      chlCustom.cpp.withLineHighlight("1,5-6")(
        """
          #include <iostream>

          int main()
          {
            std::cout << "Hello World!" << std::endl;
            return 0;
          }
        """
      ),
      h3("No line numbers"),
      chlCustom.scala("""
        object HelloWorld {
          def main(args: Array[String]): Unit = {
            println("Hello, world!")
          }
        }
      """)
    )
  )

  val cmdSection = Section(
    "Command line",
    div(
      h3("Default"),
      chl.bash("""
        NAME="John"
        echo "Hello $NAME!"
      """),
      h3("Custom user"),
      chl.bash
        .withCmdUser("superadmin", "10.0.0.7")("""
          echo "Test, test..."
          echo "Test, test..."
          echo "Test, test..."
        """),
      h3("Custom prompt"),
      chl.batch.withCmdPrompt("my~awsome~prompt>")("""set foo=bar;"""),
      h3("""Output lines ("2-5")"""),
      chl.batch
        .withCmdPrompt("""C:\blah>""")
        .withLineHighlight("2-5")("""
          batchtest.bat
          foo
          bar
          bat
          batch
        """)
    )
  )

  val markupSection = Section(
    "HTML & CSS",
    div(
      h3("Unescaped HTML"),
      chl.markup("""
<h2>An unordered HTML list</h2>
<ul>
  <li>Coffee</li>
  <li>Tea</li>
  <li>Milk</li>
</ul>
      """.trim), // prismjs doesn't trim these...
      h3("CSS (also with Data-URI Highlight)"),
      chl.css(
        """
          div {
              border: 40px solid transparent;

              border-image: 33.334% url('data:image/svg+xml,<svg xmlns="http://www.w3.org/2000/svg" width="30" height="30"> \
                          <circle cx="5" cy="5" r="5" fill="%23ab4"/><circle cx="15" cy="5" r="5" fill="%23655"/> \
                          <circle cx="25" cy="5" r="5" fill="%23e07"/><circle cx="5" cy="15" r="5" fill="%23655"/> \
                          <circle cx="15" cy="15" r="5" fill="hsl(15, 25%, 75%)"/> \
                          <circle cx="25" cy="15" r="5" fill="%23655"/><circle cx="5" cy="25" r="5" fill="%23fb3"/> \
                          <circle cx="15" cy="25" r="5" fill="%23655"/><circle cx="25" cy="25" r="5" fill="%2358a"/></svg>');

              font: 130%/1.6 Baskerville, Palatino, serif;
          }
        """
      )
    )
  )

}

object chl extends PrismCodeHighlightComponents {}

object chlCustom extends PrismCodeHighlightComponents {
  override def showLineNumbers = false
}
