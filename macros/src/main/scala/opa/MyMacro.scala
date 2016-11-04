
import scala.annotation.StaticAnnotation
import scala.language.experimental.macros
import scala.reflect.macros.blackbox


class TestAnnotation extends StaticAnnotation {
  def macroTransform(annottees: Any*) = macro MyMacro.impl
}

object MyMacro {
  def impl(c: blackbox.Context)(annottees: c.Expr[Any]*): c.Expr[Any] = {
    import c.universe._
    val result: c.universe.Tree =
      annottees.map(_.tree).toList match {
        case q"""object $objectName {
                  ..$body
                }""" :: Nil ⇒
          println("FFF")
              q"""object $objectName {
                  ..$body
                  def lello = println("GGG")
                }"""
        case branch :: Nil ⇒
          println("DDD")
          println("DDD",branch)
          //???
          branch
        case branches ⇒
          println("AAA")
          println(branches)
          ???
      }
    c.Expr[Any](result)
  }

/*
  def impl2(c: blackbox.Context)(annottees: c.Expr[Any]*): c.Expr[Any] = {
    import c.universe._
    val result = {
      annottees.map(_.tree).toList match {
        case q"$mods def $methodName[..$tpes](...$args): $returnType = { ..$body }" :: Nil => {
          q"""$mods def $methodName[..$tpes](...$args): $returnType =  {
            val start = System.nanoTime()
            val result = {..$body}
            val end = System.nanoTime()
            println(${methodName.toString} + " elapsed time: " + (end - start) + "ns")
            result
          }"""
        }
        case _ => c.abort(c.enclosingPosition, "Annotation @Benchmark can be used only with methods")
      }
    }
    c.Expr[Any](result)

  }*/
}


/*
trait Test {
  0x56e; def a(): String


  0x56e;
  ('x , 'l)


  rel 'zones id 0x56e

  case class Model(id: Int, name: Symbol, fields: Prop*)
  trait Prop
  case class Repeated(id: Int, name: Symbol, valueModel: Model, prop: MetaProp*) extends Prop
  case class One(id: Int, name: Symbol, valueModel: Model, prop: MetaProp*) extends Prop
  trait MetaProp
  lazy val MInt = Model(???)
  def requiresLife(m: Model): Model

  lazy val GeoPoint = Model(???)
  lazy val Urk = Model(???)


  lazy val Zone = Model(0x0154, 'Zone,
    Repeated(0x1003, 'osmPoints, GeoPoint)
  )
  lazy val StorageZone = Model(0x0354, 'StorageZone, Zone.fields:_*,
    One(0x01b3, 'startEnterPoint, MInt),
    One(0x1003, 'urk, requiresLife(Urk))
  )


  (, 'zones, )
}
*/