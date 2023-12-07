package files.prismjs

import scala.collection.decorators.*
import ba.sake.hepek.prismjs.*
import files.Imports.Bundle.*, Tags.*, Grid.*

val chl = PrismCodeHighlightComponents.default

object PrismJSExample extends StaticPage with PrismDependencies {

  override def navbar = None

  override def pageSettings =
    super.pageSettings.withTitle("PrismJS")

  /*override def prismSettings =
    super.prismSettings
      .withTheme(PrismThemes.Twilight)
      .withCopyToClipboard(false)
      .withShowInvisibles(true)
      .withShowLanguage(false)*/

  override def pageContent = {
    val sectionContents = sections
      .map { s =>
        frag(h2(s.name), s.content)
      }
      .intersperse(hr())
    row(sectionContents)
  }

  def sections =
    List(basicsSection, lineNumsSection, highlightLinesSection, cmdSection, markupSection)

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
        " code."
      ),
      h3(
        "File fetched with AJAX(",
        a(
          href := "https://github.com/sake92/hepek-core/blob/master/src/main/java/ba/sake/hepek/core/Renderable.java"
        )("this one"),
        ")"
      ),
      chl.java.ajax(
        "https://raw.githubusercontent.com/sake92/hepek-core/master/src/main/java/ba/sake/hepek/core/Renderable.java"
      ),
      h3(
        "File from Github API (",
        a(href := "https://github.com/TheAdnan/focustube/blob/master/index.js")("this one"),
        ")"
      ),
      chl.javascript.github("TheAdnan", "focustube", "index.js"),
      h3(
        "File from a Gist (",
        a(href := "https://gist.github.com/codeBelt/65a82e76597f2fb6c2af#file-brick-ts")(
          "this one"
        ),
        ")"
      ),
      chl.typescript.gist("65a82e76597f2fb6c2af", Option("Brick.ts"))
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
      h3("No line numbers"),
      chl
        .withShowLineNumbers(false)
        .scala("""
          object HelloWorld {
            def main(args: Array[String]): Unit = {
              println("Hello, world!")
            }
          }
        """)
    )
  )

  val highlightLinesSection = Section(
    "Highlight lines",
    div(
      h3("""Highlight some lines ("1,5-6"), perfect for tutorials"""),
      chl.cpp.withLineHighlight("1,5-6")(
        """
          #include <iostream>

          int main()
          {
            std::cout << "Hello World!" << std::endl;
            return 0;
          }
        """
      )
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
        .withUser("superadmin", "10.0.0.7")("""
          echo "Test, test..."
          echo "Test, test..."
          echo "Test, test..."
        """),
      h3("Custom prompt"),
      chl.batch.withPrompt("my~awsome~prompt>")("""set foo=bar;"""),
      h3("""Output lines ("2-5")"""),
      chl.batch
        .withPrompt("""C:\blah>""")
        .withOutputLines("2-5")("""
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
      """.trim), // files.prismjs doesn't trim these...
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
