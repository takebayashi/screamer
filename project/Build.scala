import sbt._
import Keys._
import PlayProject._

object ApplicationBuild extends Build {

    val appName         = "screamer"
    val appVersion      = "0.0.3"

    val appDependencies = Seq(
      "com.mongodb.casbah" % "casbah_2.9.0-1" % "2.1.5.0",
      "joda-time" % "joda-time" % "2.0",
      "org.joda" % "joda-convert" % "1.1",
      "org.scala-tools.time" % "time_2.9.1" % "0.5",
      "org.markdownj" % "markdownj" % "0.3.0-1.0.2b4"
    )

    val main = PlayProject(appName, appVersion, appDependencies).settings(defaultScalaSettings:_*).settings(
      // Add your own project settings here      
    )

}
