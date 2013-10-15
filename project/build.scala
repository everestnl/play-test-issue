import sbt._
import sbt.Keys._

object HelloBuild extends Build {
  override def settings = super.settings ++ sharedSettings
  
  val root = (
    Project("hello-play-root", file("."))
    aggregate(web,service)
  ) 
  
  lazy val web = play.Project("hello-play-web", "1.0-SNAPSHOT", webDeps, path = file("web"))
    .settings(webSettings:_*)
	.dependsOn(service)

  lazy val webDeps = Seq(
  // Select Play modules
  //jdbc,      // The JDBC connection pool and the play.api.db API
  //anorm,     // Scala RDBMS Library
  //javaJdbc,  // Java database API
  //javaEbean, // Java Ebean plugin
  //javaJpa,   // Java JPA plugin
  //filters,   // A set of built-in filters
  //javaCore,  // The core Java API
  // WebJars pull in client-side web libraries
  "org.webjars" %% "webjars-play" % "2.1.0-3",
  "org.webjars" % "bootstrap" % "2.3.1"
  // Add your own project dependencies in the form:
  // "group" % "artifact" % "version"
)
  lazy val webSettings = play.Project.defaultScalaSettings  ++ Seq(
  )

  lazy val service = Project("hello-play-service", file("service"))
  
  lazy val sharedSettings = Seq[Setting[_]](
      scalaVersion := "2.10.2"
  )
}
