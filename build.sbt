lazy val root = (project in file(".")).
  settings(
    name := "pureconfig-test",
    version := "0.1",
    scalaVersion := "2.11.12",
    mainClass in Compile := Some("com.github.jasonheo")
  )

libraryDependencies ++= Seq(
  "com.github.pureconfig" %% "pureconfig" % "0.9.1"
)

assemblyJarName in assembly := "pureconfig-test.jar"

assemblyMergeStrategy in assembly := {
  case "META-INF/MANIFEST.MF" => MergeStrategy.discard
  case _ => MergeStrategy.first
}