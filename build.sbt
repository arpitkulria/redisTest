import play.PlayScala
import scoverage.ScoverageKeys._

name := "redisTest"

version := "1.0"

scalaVersion := "2.11.7"



val scoverageSettings = Seq(
  coverageExcludedPackages := "<empty>;controllers.javascript*;views.*;router;Reverse.*;actors.*;assets.*;controllers.*;models.*;utils.*;views.*",
  coverageExcludedFiles := "",
  coverageMinimum := 80,
  coverageFailOnMinimum := true
)


scalaVersion := "2.11.7"



libraryDependencies ++= Seq(
  jdbc, anorm, filters, cache, ws,
  "mysql" % "mysql-connector-java" % "5.1.33",
  "com.etaty.rediscala" %% "rediscala" % "1.4.0",
  "com.github.nscala-time" %% "nscala-time" % "1.4.0",
  "commons-codec" % "commons-codec" % "1.9",
  "com.typesafe.akka" % "akka-testkit_2.11" % "2.3.11",
  "org.scalatest" %% "scalatest" % "2.2.4" % "test",
  "org.redisson" % "redisson" % "2.2.5"

)

scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature", "-language:reflectiveCalls",
  "-language:implicitConversions", "-language:postfixOps", "-language:dynamics","-language:higherKinds",
  "-language:existentials", "-language:experimental.macros", "-Xmax-classfile-name", "140")

resolvers ++= Seq("rediscala" at "http://dl.bintray.com/etaty/maven", "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases")

//javascriptEntryPoints <<= (sourceDirectory in Compile)(base =>
//((base / "assets" ** "*.js") --- (base / "assets" ** "_*")).get)


fork in Test := false

lazy val root = (project in file(".")).settings(scoverageSettings: _*).enablePlugins(PlayScala)
