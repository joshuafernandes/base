import sbt.Keys._
import sbt._
//import xerial.sbt.Pack._
import com.earldouglas.xwp.TomcatPlugin

object build extends Build {
  type Sett = Def.Setting[_]

  val versions = Map(
    "base" -> "1.0.0",
    "scala" -> "2.11.7",
    "spray" -> "1.3.3",
    "akka" -> "2.4.2",
    "specs2" -> "2.3.13-scalaz-7.1.0-RC1"
  )

  val appDependencies = Seq(
    "io.spray"          %% "spray-can"         % versions("spray"),
    "io.spray"          %% "spray-http"        % versions("spray"),
    "io.spray"          %% "spray-io"          % versions("spray"),
    "io.spray"          %% "spray-servlet"     % versions("spray"),
    "io.spray"          %% "spray-util"        % versions("spray"),
    "io.spray"          %% "spray-routing"     % versions("spray"),
    "com.typesafe.akka" %% "akka-actor"        % versions("akka"),
    "org.slf4j"         % "slf4j-log4j12"      % "1.7.18",
    ("io.argonaut"       % "argonaut"           % "6.1").changing.cross(CrossVersion.binary),
    "io.spray"          %% "spray-testkit"     % versions("spray") % "test" exclude("org.specs2", "specs2_2.11"),
    "org.specs2"        %% "specs2-core"       % versions("specs2") % "test",
    "org.specs2"        %% "specs2-junit"      % versions("specs2") % "test",
    "org.specs2"        %% "specs2-mock"       % versions("specs2") % "test"
  )

  val project = Project (
    id = "base" ,
    base = file("."),
    settings = Defaults.coreDefaultSettings ++
      Seq(
        organization := "com.example",
        name := "base",
        version := versions("base"),
        scalaVersion := versions("scala"),
        libraryDependencies ++= appDependencies
      )
  ).enablePlugins(TomcatPlugin)

}


