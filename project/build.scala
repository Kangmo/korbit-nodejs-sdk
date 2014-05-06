import sbt._
import Keys._

object CoreBuild extends Build {
  lazy val root = Project("root", file(".")) aggregate(test)

  lazy val test: Project = Project("test", file("test"),
    settings = Defaults.defaultSettings ++ Seq(
      name := "scala-node-test",
      version := "0.2.0",
      scalaVersion := "2.10.2",
      resolvers ++= Seq(
        DefaultMavenRepository,
        "scala.js" at "http://repo.scala-js.org/repo/releases/"
      ),
      libraryDependencies ++= Seq("org.specs2" %% "specs2" % "2.3.7" % "test")
    )
  ) dependsOn(main % "compile")

  lazy val main: Project = Project("main", file("main"), delegates = root :: Nil,
    settings = Defaults.defaultSettings ++ Seq(
      name := "scala-node-main",
      version := "0.2.0",
      scalaVersion := "2.10.2",
      resolvers ++= Seq(
        DefaultMavenRepository,
        "scala.js" at "http://repo.scala-js.org/repo/releases/",
        "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"
      ),
      libraryDependencies ++= Seq(
        "com.fasterxml.jackson.core" % "jackson-databind" % "2.3.3",
        "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.3.0",
        "com.typesafe.akka" % "akka-actor_2.10" % "2.3.2"
      )
    )
  )
}
