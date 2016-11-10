

object TestApp extends App{
    var l = List[Array[Byte]]()
    (1 to 10000000).foreach{ i =>
        val b = new Instant.Builder()
        l = Instant.ADAPTER.encode(b.seconds(i).nanos(i).build()) :: l
    }
    val start = System.currentTimeMillis
    
    //var r = List[Test.Instant]()
    while(l.nonEmpty) {
      Instant.ADAPTER.decode(l.head)
      l = l.tail
    }

    //l.map(b=>Instant.parseFrom(b))
    println(System.currentTimeMillis-start)
    //println(r.size)
}