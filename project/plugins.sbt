
resolvers += Resolver.typesafeRepo("releases")
resolvers += Resolver.sonatypeRepo("public")
resolvers += Resolver.sonatypeRepo("snapshots")

addSbtPlugin("com.lucidchart" % "sbt-scalafmt" % "1.14")
addSbtPlugin("com.typesafe.sbt" % "sbt-web" % "1.4.3")
addSbtPlugin("ba.sake" % "sbt-hepek" % "0.1.0-SNAPSHOT")
