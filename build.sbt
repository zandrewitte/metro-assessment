import sbt._

// -----------------------------------------------------------------------------
// Loader Job
// -----------------------------------------------------------------------------

lazy val loader =
  project
    .in(file("metro-loader-job"))
    .configs(IntegrationTest)
    .settings(
      Settings.commonSettings,
      Defaults.itSettings
    )