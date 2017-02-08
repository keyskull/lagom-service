import sbt.Keys.scalaVersion

import scala.concurrent.duration._
// Mind that the import is needed.

name := "lagom-service"
javacOptions ++= Seq("-encoding", "UTF-8")
coverageEnabled := true
organization in ThisBuild := "me.keyskull"
scalaVersion in ThisBuild := "2.11.8"

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


lazy val commonSettings = Seq(
  version := "1.0-SNAPSHOT",
  javacOptions ++= Seq("-encoding", "UTF-8"),
  coverageEnabled := true,
    libraryDependencies ++= Seq(
    lagomScaladslApi,
    lagomScaladslTestKit,
    "com.softwaremill.macwire" %% "macros" % "2.2.5" % "provided",
    "org.scalatest" %% "scalatest" % "3.0.1" % "test"
  )
)


lazy val `auth-api` = (project in file("auth-api")).
  settings(commonSettings: _*).settings(
  libraryDependencies ++= Seq(
    "com.google.firebase" % "firebase-server-sdk" % "3.0.3"

  )
)

lazy val `android-auth-impl` = (project in file("android-auth-impl")).
  enablePlugins(PlayScala, LagomScala).
  settings(commonSettings: _*).
  settings(
    libraryDependencies ++= Seq(cache , ws),
    lagomServicePort := 4329
  ).
  dependsOn(`auth-api`)

