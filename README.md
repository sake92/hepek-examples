# hepek-examples

This projects contains some examples of using [sbt-hepek](https://github.com/sake92/sbt-hepek).  
Some parts of this project will eventually be extracted to a library.  
Feel free to make suggestions by raising an issue or by pinging me on Twitter @[sake_92](https://twitter.com/sake_92). :)

## Quick intro to sbt-hepek

What is it? In a nutshell it is an [SBT](http://www.scala-sbt.org/) plugin for rendering Scala `object`s to files.

The main traits that sbt-hepek plugin uses are `Renderable` and `RelativePath` (found in [hepek-core](https://github.com/sake92/hepek-core) library).  
`Renderable` **extends** `RelativePath` which means that every *renderable* has its *path* where it **will be rendered** when **hepek** is run.
Simple, right? You only need an **object** that extends `Renderable` and implements its `render: String` method. That will be the content of the file.  

To render the objects to files you start the sbt shell and run `hepek`.  
It will compile your sources and render the files to the `hepekTarget` folder (currently it is "target/web/public/main/").

----------------------------------------

Now, the `RelativePath` has some interesting use cases. It is needed for `Renderable` to be rendered properly.  
The idea is that all resources that extend `RelativePath` will have path reflecting it's class' **package**.  
E.g. if you have a resource called "text.txt" in a package `com.myorg` 
its path would be "com/myorg/text.txt", relative to `hepekTarget` folder.
- First usecase, concerns files that **will be inserted manually** along with the rendered files (see `RawRelativePath`), 
for example a JavaScript file or an image.  
This is handy when we want to create a relative path between two files, like a reference to js file in HTML (see method `relTo` in class `Page`, example 2).  
Here we need to give the name of the resource **manually**, like "myScript.js" or "image.jpg"...

- Second usecase, regarding `Renderable`s (see `ClassRelativePath`) is to use class'/object's full name, 
for example if you have an object called `MyText` in a package `com.myorg` 
it would be rendered to a file "com/myorg/MyText", relative to `hepekTarget` folder.  
That is, the **name of the file** would be **object's name**.  


## Example 1: Plain text

Here is a very simple example. You want to render some text to file but you need **variables**, 
or to populate it from database, or to **call a web service** to fetch some code you want to display in your tutorial.  
You are too lazy (like me) to learn a new templating syntax for this, and of course, there is no auto-complete in your favorite IDE for it.  
So, you take "sbt-hepek" plugin, extend a class or two and you're ready to go:

```scala
object MyText extends ClassRelativePath with Renderable {

  override def extension: String = "txt"

  override def render: String = {
    val loremIpsum: String = """
      Lorem ipsum dolor sit amet, consectetur adipiscing elit,
      sed do eiusmod tempor incididunt ut labore et dolore magna aliqua...
    """
    // this is pure Scala code, you can do anything here
    val myList = for (i <- 1 to 10) yield {
      s"$i. line"
    }
    loremIpsum + myList.mkString("\n")
  }
}
```

Boilerplate code for this is only `extends ClassRelativePath with Renderable` and overriden `render` method.  
Of course, you would make a base class for all text files and extend it.  
Possibly with some common wrapping text or something like that...  
The point is that you are in charge, not your static site generator or something else.

You could also render XML or JSON files with your favorite library (for whatever reason, type-safety maybe?).  
Scala's ecosystem is very rich with these, especially with JSON support. 

## Example 2: HTML

This is where it gets interesting. The main reason why I made this plugin in the first place.  
Hepek as an "engine" for static websites (blogs mainly).

First, we'll make a template for our HTML pages. All will have the same look and feel, [Bootstrap](http://getbootstrap.com/) of course,
what did you expect from a backend developer like me? xD  
Here it is:

```scala
trait Page extends ClassRelativePath with Renderable {

  def title: String

  def body: String  

  override def render: String = {
    s"""
      |<!DOCTYPE html>
      |<html lang="en">
      |  <head>
      |    <meta charset="utf-8">
      |    <meta name="viewport" content="width=device-width, initial-scale=1">
      |    <title>$title</title>
      |    <!-- Include Bootstrap CSS -->
      |    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
      |  </head>
      |  <body>
      |    <div class="container-fluid">
      |      $body
      |    </div>
      |    <!-- Include Bootstrap & jQuery libs -->
      |    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
      |    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
      |    <script src="${relTo(JS.myJs)}"></script>
      |  </body>
      |</html>
    """.stripMargin
  }
  
  def relTo(other: RelativePath): String = {
    this.relPath.toPath.getParent.relativize(other.relPath.toPath).toString
  }
}
```

Every `Page` will have a `title` and a `body`.  
Title is used for title, of course.  
Body is rendered in our HTML div with `container-fluid` CSS class.  

You'll also notice the handy `relTo` method. It is used, as we mentioned in introduction, for referring to a relative resource.  
That resource is any class that extends `RelativePath`.  
Resource can be either rendered with sbt-hepek or manually placed in folder (JS file or an image etc.).

Next, we'll create another template, a trait called `Main`:

```scala
trait Main extends Page {

  def content: String

  /* Every page will have this sidebar rendered */
  def allPages: List[Page] = List(Index, Page1, Page2)

  def sidebar: String = {
    val pageLiTags = allPages.map { p =>
      val activeClass = if (p.relPath == this.relPath) "class='active'" else ""
      val hrefToPage = relTo(p)
      s"""<li $activeClass><a href="$hrefToPage">${p.title}</a></li>"""
    }.mkString("\n")

    s"""
      |<ul class="nav nav-pills nav-stacked">
      |  $pageLiTags
      |</ul>
    """.stripMargin
  }

  override def body: String = {
    s"""
      |<div class="row">
      |  <div class="col-xs-3">$sidebar</div>
      |  <div class="col-xs-7">$content</div>
      |</div>
    """.stripMargin
  }
}
```

Sidebar contains all pages in our site (Main, Page1 and Page2). They are rendered in a `nav-pills` div.  
The contents of our blog posts are rendered in second HTML div.  
That's it, we are ready to make our first blog post:

```scala
package ba.sake.hepek.example2.pages // render to /pages folder!

object Page1 extends Main {

  override def title = "Page 1"

  override def content: String = {
    s"""    
      |<h1>$title</h1>      
      |This is my First page :) <br>
    """.stripMargin
  }
}
```

One thing left is our static resources, js and images.  
We'll create classes for both separately, since they'll be in separate folders:

```scala
package ba.sake.hepek.example2.js // notice the package! -> /js folder

/** Represents an javascript resource in /js folder */
class JSResource(name: String) extends RawRelativePath {
  override val fileName = name + ".js"
}

object JSResource {
  def apply(name: String): RelativePath = new JSResource(name)
}

object JS {
  val myJs = JSResource("myjs") // this is our js file called "myjs.js"
}
```

We can look back at our `Page` template and see that we refer to `myJs` with `src="${relTo(JS.myJs)}"`.  
If we changed the package of `Page1` for example, it would be compiled and rendered again, and the path would still work!

The same is for images. 

When you render the page it should look like this (Open "target/web/public/main/ba/sake/hepek/example2/index.html"):  

![whatever](https://upload.cc/i3/BfgtZn.png "Homepage")


You can do a `sbt clean` and then `sbt hepek` to convince yourself it works.  



