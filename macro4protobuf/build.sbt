


scalaVersion := scalaVer

scalacOptions += "-Ymacro-debug-lite"

lazy val scalaVer = "2.11.8"
lazy val commonSettings = Seq(
    scalaVersion := scalaVer,
    //libraryDependencies += "org.scala-lang" % "scala-reflect" % "2.12.0",
    //libraryDependencies += "macros" % "macros_2.11" % "1.0"
    //// for annotations (both subprojects):
    //resolvers += Resolver.sonatypeRepo("snapshots"),
    resolvers += Resolver.sonatypeRepo("releases"),
    addCompilerPlugin("org.scalamacros" % "paradise" % "2.1.0" cross CrossVersion.full)
)
lazy val core = project.settings(commonSettings:_*).dependsOn(macros).settings(
    scalacOptions += "-Ymacro-debug-lite"
)
lazy val macros = project.settings(commonSettings:_*).settings(
  libraryDependencies += "org.scala-lang" % "scala-reflect" % scalaVer
)
lazy val root = project.in(file(".")).settings(commonSettings:_*).aggregate(core,macros)

//