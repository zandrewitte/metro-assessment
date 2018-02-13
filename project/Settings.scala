import sbt._
import sbt.Keys._
import wartremover.WartRemover.autoImport._
import com.lucidchart.sbt.scalafmt.ScalafmtCorePlugin.autoImport._
import com.lucidchart.sbt.scalafmt.ScalafmtSbtPlugin.autoImport.Sbt

object Settings {

  val commonSettings: Seq[Setting[_]] = buildSettings ++ wartRemoverSettings ++ scalaFmtSettings

  // -----------------------------------------------------------------------------
  // Common Settings
  // -----------------------------------------------------------------------------
  lazy val buildSettings = Seq(
    organization := "com.zantech",
    scalaVersion := Dependencies.Version.scala,
    crossScalaVersions := Seq(scalaVersion.value, Dependencies.Version.scala),
    organization := "com.zantech",
    scalacOptions ++= Seq(
      "-unchecked",
      "-deprecation",
      "-language:_",
      "-target:jvm-1.8",
      "-encoding",
      "UTF-8",
      "-feature",
      "-Xfatal-warnings"
    ),
    unmanagedSourceDirectories.in(Compile) := Seq(scalaSource.in(Compile).value),
    unmanagedSourceDirectories.in(Test) := Seq(scalaSource.in(Test).value),
    parallelExecution in Test := false
  )

  // -----------------------------------------------------------------------------
  // Wart Remover settings
  // -----------------------------------------------------------------------------
  lazy val wartRemoverSettings = Seq(
    wartremoverErrors in (Compile, compile) ++= Warts.allBut(
      Wart.Any,
      Wart.ImplicitParameter,
      Wart.NonUnitStatements
    ),
    wartremoverExcluded ++= sourceManaged.value.**("*.scala").get
  )

  // -----------------------------------------------------------------------------
  // Scala Format settings
  // -----------------------------------------------------------------------------
  lazy val scalaFmtSettings = Seq(
    scalafmtOnCompile := true,
    scalafmtOnCompile.in(Sbt) := true,
    scalafmtVersion := "1.4.0"
  )

}
