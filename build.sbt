name := "mac"

version := "1.0"

scalaVersion := "2.11.7"


val postgresqlVersion = "9.4-1201-jdbc41"

val common_settings = Defaults.coreDefaultSettings ++
  Seq(
    organization := "com.cpawali",
    scalaVersion in ThisBuild := "2.11.7",
    scalacOptions ++= Seq("-unchecked", "-feature", "-deprecation"),
    libraryDependencies ++= Seq(
      "com.typesafe.slick" %% "slick" % "3.0.0-RC3",
      "org.slf4j" % "slf4j-nop" % "1.6.4",
      "org.postgresql" % "postgresql" % postgresqlVersion,
      "org.json4s" %% "json4s-jackson" % "3.2.11" ,
      "joda-time" % "joda-time" % "2.8.2",
      "com.typesafe.slick" %% "slick-codegen" % "3.0.0",
      "org.scalatest" %% "scalatest" % "3.0.1",
      "net.debasishg" %% "redisclient" % "3.4"
    ))

val cpawali_work = Project(id = "utilities", base = file(".")).aggregate(connectivity, jsonUtilities)
lazy val connectivity = Project(id = "connectivity", base = file("connectivity"), settings = common_settings)
lazy val jsonUtilities = Project(id = "jsonUtilities", base = file("jsonUtilities"),settings = common_settings)