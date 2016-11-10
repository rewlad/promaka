package test

object ThriftTest extends App {

  val transport = new org.apache.thrift.transport.TMemoryBuffer(1024)
  val protocol = new org.apache.thrift.protocol.TJSONProtocol(transport)
  //val serializer = new org.apache.thrift.TSerializer(new org.apache.thrift.protocol.TJSONProtocol.Factory())
  //println(serializer.toString(user))

  val user = thrift.User(37,"Sergei")
  user.write(protocol)
  val jsonStr = new String(transport.getArray,"UTF-8")
  println(jsonStr)

  println(thrift.User.decode(protocol))

  // println(transport.inspect())


}