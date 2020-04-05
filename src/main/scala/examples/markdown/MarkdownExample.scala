package examples.markdown

import scalatags.Text.all._
import examples.Imports.Bundle._
import examples.Index

object MarkdownExample extends StaticPage {

  override def pageSettings =
    super.pageSettings.withTitle("Markdown Example")

  override def pageContent = frag(
    s"""
    # Markdown Example

    ---
    # An example header
    ## A sub-heading
    ### Another sub-heading
    #### A smaller sub-heading
    
    ---
    ## Emphasis
    To emphasize some words with italics simply *surround them with single asterisks or underscores.* 
    
    In order to make words bold then **surround them with double asterisks or underscores.** 

    ---
    ## Code Snippets
    Code can be easily formatted in markdown as well.
    It can be `inline code`, or it can be code blocks:

    ```scala
    object MarkdownExample extends StaticPage {

      override def pageSettings =
        super.pageSettings.withTitle("Markdown Example")
    }
    ```
    
    ---
    ## Lists
    Markdown can be used to make bullet lists:
      - apple,
      - banana,
      - orange,
      
    and numbered lists:
      1. One.
      1. Two.
      1. Three.

    ---
    ## Links and Images
    Click [here](https://github.com/adam-p/markdown-here/wiki/Markdown-Cheatsheet "Github Markdown Cheatsheet") to see more markdown examples.
    
    Other pages in this site can be [referenced](${Index.ref}) as well.

    ---
    Images:

    ![example image](https://images.unsplash.com/photo-1518118238797-500670daedf7?ixlib=rb-0.3.5&ixid=eyJhcHBfaWQiOjEyMDd9&s=85cb561f6a237a4926da07acaca7b019&auto=format&fit=crop&w=1350&q=80)
    """.md
  )
}
