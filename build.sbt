organization := "ba.sake"

name := "hepek-examples"

version := "0.0.0-SNAPSHOT"

scalaVersion := "2.11.8"

enablePlugins(HepekPlugin)
enablePlugins(SbtWeb)

// enable this to see more verbose output
//logLevel in hepek := Level.Debug

libraryDependencies ++= Seq(
  "org.webjars" % "jquery" % "3.1.1-1",
  "org.webjars" % "bootstrap" % "3.3.7-1"
)