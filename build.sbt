name := "spark-neighbors"

organization := "com.takt"

description := "Spark-based approximate nearest neighbor search using locality-sensitive hashing"

version := "0.3.0"

scalaVersion := "2.11.8"

spName := "takt/spark-neighbors"

sparkVersion := "2.0.2"

sparkComponents := Seq("core", "mllib")

val testSparkVersion = settingKey[String]("The version of Spark to test against.")

testSparkVersion := sys.props.get("spark.testVersion").getOrElse(sparkVersion.value)

libraryDependencies ++= Seq(
  "org.scalanlp" %% "breeze" % "0.12",
  "org.scalatest" %% "scalatest" % "2.2.4" % "test"
)

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % testSparkVersion.value % "test" force(),
  "org.apache.spark" %% "spark-mllib" % testSparkVersion.value % "test" force(),
  "org.scala-lang" % "scala-library" % scalaVersion.value % "compile"
)

// This is necessary because of how we explicitly specify Spark dependencies
// for tests rather than using the sbt-spark-package plugin to provide them.
spIgnoreProvided := true

parallelExecution in Test := false

publishArtifact in Test := false

publishMavenStyle := true

spIncludeMaven := true

licenses += ("MIT", url("http://opensource.org/licenses/MIT"))

// credentials += Credentials(Path.userHome / ".ivy2" / ".spark-package-credentials")

/*
publishTo := {
  val nexus = "https://oss.sonatype.org/"
  if (version.value.endsWith("SNAPSHOT"))
    Some("snapshots" at nexus + "content/repositories/snapshots")
  else
    Some("releases"  at nexus + "service/local/staging/deploy/maven2")
}
*/

publishTo := Some("somatix-dev s3 releases repo" at "s3://releases.mvn-repo.takt.com")

pomExtra := (
  <url>https://github.com/TaktInc/spark-neighbors</url>
  <scm>
    <url>git@github.com:TaktInc/spark-neighbors.git</url>
    <connection>scm:git:git@github.com:TaktInc/spark-neighbors.git</connection>
  </scm>
  <developers>
    <developer>
      <id>takt</id>
      <name>Takt Inc</name>
      <url>https://github.com/TaktInc</url>
    </developer>
  </developers>)

pomIncludeRepository := { _ => false }

useGpg := true
