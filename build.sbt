lazy val root = (project in file(".")).
  settings(
    name := "Sudoku Sensei",
    version := "2021.1",
    scalaVersion := "3.0.0-M3"
  )

libraryDependencies += "org.scalameta" %% "munit" % "0.7.20" % Test
testFrameworks += new TestFramework("munit.Framework")