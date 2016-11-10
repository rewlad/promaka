

object TestApp extends App{
    var l = List[Array[Byte]]()
    (1 to 10000000).foreach{ i =>
        val b = Test.Instant.newBuilder()
        l = b.setSeconds(i).setNanos(i).build().toByteArray() :: l
    }
    val start = System.currentTimeMillis
    
    //var r = List[Test.Instant]()
    while(l.nonEmpty) {
      Test.Instant.parseFrom(l.head)
      l = l.tail
    }

    //l.map(b=>Test.Instant.parseFrom(b))
    println(System.currentTimeMillis-start)
    //println(r.size)
}