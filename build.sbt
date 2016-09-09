name := """play-java-2.3"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  "com.typesafe.play.plugins" %% "play-plugins-redis" % "2.3.1",
  "mysql" % "mysql-connector-java" % "5.1.18",
  javaJdbc,
  javaEbean,
  cache,
  javaWs,
  filters
)


fork in run := true
