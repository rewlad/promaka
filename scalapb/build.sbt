name := "kafka_test"

version := "1.0.1"

scalaVersion := "2.11.8"

////

PB.targets in Compile := Seq(scalapb.gen() -> (sourceManaged in Compile).value)
