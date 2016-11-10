
package test0

import scala.annotation.StaticAnnotation

class Decimal
class Scale(id: Int) extends StaticAnnotation

import schema._

@Schema object Test1 {
  @Id(0x0001) trait ModelA {
    def fieldA: Int
  }
  @Id(0x0001) trait ModelB {
    def fieldB: ModelC
  }
  @Id(0x0001) trait ModelC extends ModelA with ModelB {
    def fieldC: ModelB
  }
}

@Schema object Test0 {
  trait GeoPoint {
    @Id(0x000b) def latitude: Decimal @Scale(6)
    @Id(0x000c) def longitude: Decimal @Scale(6)
  }
  @Id(0x0154) trait Zone {
    @Id(0x1003) def osmPoints: List[GeoPoint]
  }
}
