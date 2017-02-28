import sbt.Keys.scalaVersion

import scala.concurrent.duration._
// Mind that the import is needed.

enablePlugins(JavaServerAppPackaging)
unmanagedBase := baseDirectory.value / "lib"

resolvers += "lightbend-contrail" at "https://dl.bintray.com/typesafe/commercial-maven-releases"

name := "lagom-service"
javacOptions ++= Seq("-encoding", "UTF-8")
coverageEnabled := true
organization in ThisBuild := "me.keyskull"
scalaVersion in ThisBuild := "2.11.8"
bintrayOrganization in ThisBuild := Some("big2")
bintrayOmitLicense in ThisBuild := false

// ServiceLocator
lagomServiceLocatorPort in ThisBuild := 8001

//-------------Cassandra
//lagomCassandraEnabled in ThisBuild := false
lagomCassandraPort in ThisBuild := 9042
lagomCassandraMaxBootWaitingTime in ThisBuild := 0.1.seconds
//lagomCassandraJvmOptions in ThisBuild :=
//  Seq("-Xms256m", "-Xmx1024m", "-Dcassandra.jmx.local.port=4099",
//    "-DCassandraLauncher.configResource=dev-embedded-cassandra.yaml") // these are actually the default jvm options


//---------------Kafka
//lagomKafkaEnabled in ThisBuild := false
//lagomKafkaAddress in ThisBuild := "localhost:10000"
lagomKafkaPort in ThisBuild := 9092
lagomKafkaZookeperPort in ThisBuild := 2181
lagomKafkaPropertiesFile in ThisBuild := Some(file(".") / "Kafka" / "server.properties")
//lagomKafkaJvmOptions in ThisBuild := Seq("-Xms256m", "-Xmx1024m") // these are actually the default jvm options

val macwire = "com.softwaremill.macwire" %% "macros" % "2.2.5" % "provided"
val scalaTest = "org.scalatest" %% "scalatest" % "3.0.1" % "test"

lazy val commonSettings = Seq(
  version := "1.0-SNAPSHOT",
  javacOptions ++= Seq("-encoding", "UTF-8"),
  coverageEnabled := true,
  libraryDependencies ++= Seq(
    lagomScaladslApi,
    lagomScaladslServer % Optional,
    lagomScaladslTestKit,
    Cinnamon.library.cinnamonCHMetrics,
    Cinnamon.library.cinnamonCHMetricsStatsDReporter,
//    Cinnamon.library.cinnamonSandbox,
    macwire,scalaTest
  )
)

lazy val `Cinnamon-Agent` = (project in file("Cinnamon-Agent")).
  enablePlugins(Cinnamon).
  settings(commonSettings: _*).settings(
  cinnamon in run := true,
  cinnamon in test := true,
  cinnamonLogLevel := "INFO",
  libraryDependencies ++= Seq(
    Cinnamon.library.cinnamonAkka,
    "com.typesafe.conductr" %% "scala-conductr-lib-common" % "1.6.1",
    "com.typesafe.akka" %% "akka-actor" % "2.4.6"
  )
)

lazy val `auth-api` = (project in file("auth-api")).
  settings(commonSettings: _*).settings(
  libraryDependencies ++= Seq()
)

lazy val `user-api` = (project in file("user-api")).
  settings(commonSettings: _*).settings(
  libraryDependencies ++= Seq()
)

lazy val `android-auth-impl` = (project in file("android-auth-impl")).
  enablePlugins(PlayScala && LagomPlay).
  settings(commonSettings: _*).
  settings(
    libraryDependencies ++= Seq(cache, ws
      //      Cinnamon.library.cinnamonLagom
    ),
    lagomServicePort := 4329
  ).
  dependsOn(`auth-api`)

lazy val `user-impl` = (project in file("user-impl")).
  enablePlugins(LagomScala, Cinnamon).
  settings(commonSettings: _*).
  settings(
    libraryDependencies ++= Seq(cache, ws,
      lagomScaladslCluster,
      lagomScaladslPersistenceCassandra,//Cassandra
      Cinnamon.library.cinnamonLagom,
     "com.auth0" % "java-jwt" % "3.1.0",
      "com.google.firebase" % "firebase-server-sdk" % "3.0.3",
      "org.scalameta" %% "scalameta" % "1.4.0"
    ),
    lagomServicePort := 2552
  ).settings(lagomForkedTestSettings: _*).
  dependsOn(`user-api`)

aggregateProjects(`Cinnamon-Agent`, `auth-api`,`user-api`,`android-auth-impl`,`user-impl`)
