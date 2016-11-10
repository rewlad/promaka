package test

import java.time.Instant

import com.google.protobuf.GeneratedMessage
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}
import org.apache.kafka.common.TopicPartition
import org.apache.kafka.common.serialization.{ByteArrayDeserializer, ByteArraySerializer}

import scala.collection.JavaConverters.mapAsJavaMapConverter
import scala.collection.JavaConverters.iterableAsScalaIterableConverter
import scala.collection.JavaConverters.seqAsJavaListConverter

object Config {
  def getHost = Map[String,Object](
    "bootstrap.servers" → "localhost:9092"
  )
  def topic = "my-topic"
}

object ProducerTest extends App {
  val props = Config.getHost ++ Map[String,String](
    "acks" → "all",
    "retries" → "0",
    "batch.size" → "16384",
    "linger.ms" → "1",
    "buffer.memory" → "33554432"
  )
  val serializer = new ByteArraySerializer
  val producer = new KafkaProducer[Array[Byte],Array[Byte]](props.asJava, serializer, serializer)

  //5000000 ~ 1/2 min
  (1 to 5000000).foreach { i ⇒
    producer.send(
      new ProducerRecord(
        Config.topic,
        ToBytes(Instant.now()),
        ToBytes(BigDecimal(i))
      )
    )
    if(i % 100000 == 0) println(i)
  }
  producer.close()
}

object ToBytes {
  def apply(value: Object) = value match {
    case v: Instant ⇒ inner("0f2155cb-684b-482e-a127-ab1fc8389137", test.Instant(v.getEpochSecond, v.getNano).toByteArray)
    case v: BigDecimal ⇒ inner("cbaf5b18-2fc0-40e0-9d6a-8ee07d5c2924", v.toString.getBytes("UTF-8"))
  }
  def inner(typeName: String, value: Array[Byte]) = {
    test.Unknown(typeName, com.google.protobuf.ByteString.copyFrom(value)).toByteArray
  }
}

object FromBytes {
  def apply(bytes: Array[Byte]): Object = {
    val u = test.Unknown.parseFrom(bytes)
    u.dataType match {
      case "0f2155cb-684b-482e-a127-ab1fc8389137" ⇒
        val t = test.Instant.parseFrom(u.data.toByteArray)
        Instant.ofEpochSecond(t.seconds, t.nanos)
      case "cbaf5b18-2fc0-40e0-9d6a-8ee07d5c2924" ⇒
        val t = new String(u.data.toByteArray, "UTF-8")
        t
        //BigDecimal(t)
    }
  }
}


object ConsumerTest extends App {
  val props = Config.getHost ++ Seq[(String,String)](
    "group.id" → "test",
    "enable.auto.commit" → "false"
  )
  val deserializer = new ByteArrayDeserializer
  val consumer = new KafkaConsumer[Array[Byte],Array[Byte]](props.asJava, deserializer, deserializer)
  //consumer.subscribe(List(Config.topic).asJava)
  val topicPartition = new TopicPartition(Config.topic,0)
  consumer.assign(List(topicPartition).asJava)
  consumer.seek(topicPartition, 0)
  def out(value: Object) = () //println(value)
  var i = 0
  val start = System.currentTimeMillis
  while(true) { // 5M msg: kafka 10s, kafka+pb+case 13s
    //println("p")
    consumer.poll(1000).asScala.foreach { rec ⇒
      out(FromBytes(rec.key))
      out(FromBytes(rec.value))
      i += 1
      if(i % 100000 == 0) println(i,System.currentTimeMillis-start)
    }
  }
}
