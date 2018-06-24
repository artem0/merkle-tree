name := "merkle-tree"

version := "1.1"

scalaVersion := "2.12.4"

libraryDependencies ++= Seq("com.twitter" %% "bijection-core" % "0.9.6",
                            "org.scalatest" %% "scalatest" % "3.0.5" % Test)