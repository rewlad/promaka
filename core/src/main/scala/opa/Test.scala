
/*
@hello
object Test extends App {
  println(this.hello)
}
*/




@TestAnnotation object Test extends App {
  class Best
  //
  @TestAnnotation2 def aaa(): Unit = { println("A") }
  //@TestAnnotation def bbb(): Unit = { println("A") }

  //(new Best)
  aaa()
}
