name := "kafka_test_main"

version := "1.0"

scalaVersion := "2.11.8"

////

libraryDependencies += "org.apache.kafka" % "kafka-clients" % "0.10.1.0"

libraryDependencies += "kafka_test" % "kafka_test_2.11" % "1.0.1" // from publish-local

