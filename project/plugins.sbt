resolvers += Resolver.typesafeRepo("releases")

resolvers += Resolver.sonatypeRepo("snapshots")

addSbtPlugin("org.scalariform" % "sbt-scalariform" % "1.6.0")

addSbtPlugin("ba.sake" % "sbt-hepek" % "0.0.1-SNAPSHOT")
