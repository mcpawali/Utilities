name := "mac"

version := "1.0"

scalaVersion := "2.11.7"


val postgresqlVersion = "9.4-1201-jdbc41"



libraryDependencies ++= Seq(
  "com.typesafe.slick" %% "slick" % "3.0.0-RC3",
  "org.slf4j" % "slf4j-nop" % "1.6.4",
  "org.postgresql" % "postgresql" % postgresqlVersion,
  "com.typesafe.slick" %% "slick-codegen" % "3.0.0"
)