name := "ticket-model-scala"

version := "0.1"

scalaVersion := "2.13.1"

libraryDependencies ++= Seq(
  "org.scalactic" %% "scalactic" % "3.1.1",
  "org.scalatest" %% "scalatest" % "3.1.1" % "test"
)
libraryDependencies += "joda-time" % "joda-time" % "2.10.5"
