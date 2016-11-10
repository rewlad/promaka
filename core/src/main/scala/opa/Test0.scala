
import scala.annotation.StaticAnnotation

class Decimal
class Scale(id: Int) extends StaticAnnotation

@Schema object Test0 {
  trait GeoPoint {
    @Id(0x000b) def latitude: Decimal @Scale(6)
    @Id(0x000c) def longitude: Decimal @Scale(6)
  }
  @Id(0x0154) trait Zone {
    @Id(0x1003) def osmPoints: List[GeoPoint]
  }
}
