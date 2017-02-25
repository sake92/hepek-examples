resolvers += Resolver.typesafeRepo("releases")
resolvers += Resolver.sonatypeRepo("snapshots") // needed for SNAPSHOT version

addSbtPlugin("ba.sake" % "sbt-hepek" % "0.0.1-SNAPSHOT")
addSbtPlugin("org.scalariform" % "sbt-scalariform" % "1.6.0")
addSbtPlugin("com.typesafe.sbt" % "sbt-web" % "1.4.0")
