ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / scalaVersion := "3.3.5"  // 或改用 2.13.12

lazy val root = (project in file("."))
  .settings(
    name := "scala_learn",
    libraryDependencies ++= Seq(
      "org.scalanlp" %% "breeze" % "2.1.0",         // Scala 3 兼容版本
      "org.scalanlp" %% "breeze-natives" % "2.1.0", // 可选但推荐
      "org.scalatest" %% "scalatest" % "3.2.18" % Test,
      "org.apache.commons" % "commons-math3" % "3.6.1",
    )
  )
