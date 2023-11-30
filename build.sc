import $ivy.`ba.sake::mill-hepek::0.0.1`

import mill._
import mill.scalalib._
import ba.sake.millhepek.MillHepekModule

object examples extends MillHepekModule with SbtModule {

  def scalaVersion = "3.3.1"

  def ivyDeps = Agg(
    ivy"ba.sake::hepek:0.17.0",
    ivy"org.scala-lang.modules::scala-collection-contrib:0.3.0",
    ivy"com.afrozaar.wordpress:wp-api-v2-client-java:4.8.3"
  )

}
