import sbt._

object Dependencies {

  object Version {
    val akka = "2.5.9"
    val cassandraDriver = "3.2.0"
    val scala = "2.12.4"
    val mockito = "2.7.19"
    val scalaz = "7.2.18"
    val scalaTest = "3.0.1"
  }

  val scalaTest = "org.scalatest" %% "scalatest" % Version.scalaTest % Test
  val scalaz = "org.scalaz" %% "scalaz-core" % Version.scalaz
  val cassandra = "com.datastax.cassandra" % "cassandra-driver-core" % Version.cassandraDriver
  val mockito = "org.mockito" % "mockito-core" % Version.mockito % Test
  val akkaActor = "com.typesafe.akka" %% "akka-actor" % Version.akka

}
