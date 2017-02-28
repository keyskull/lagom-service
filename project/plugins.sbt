logLevel := Level.Debug
//resolvers += "JFrog Bintray" at "https://dl.bintray.com/lagom/sbt-plugin-releases/"
resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"
addSbtPlugin("com.lightbend.lagom" % "lagom-sbt-plugin" % "1.3.0-RC1")
addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.5.4")
resolvers += Resolver.url("scoverage-bintray", url("https://dl.bintray.com/sksamuel/sbt-plugins/"))(Resolver.ivyStylePatterns)
addSbtPlugin("me.lessis" % "bintray-sbt" % "0.3.0")
addSbtPlugin("org.scoverage" % "sbt-scoverage" % "1.5.0")
addSbtPlugin("com.codacy" % "sbt-codacy-coverage" % "1.3.8")
addSbtPlugin("com.lightbend.cinnamon" % "sbt-cinnamon" % "2.2.2")
//addSbtPlugin("com.typesafe.sbt" % "sbt-native-packager" % "1.2.0-M8")
