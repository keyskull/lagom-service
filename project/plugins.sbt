logLevel := Level.Debug
//resolvers += "JFrog Bintray" at "https://dl.bintray.com/lagom/sbt-plugin-releases/"
addSbtPlugin("com.lightbend.lagom" % "lagom-sbt-plugin" % "1.3.0-RC1")
resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"

addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.5.4")