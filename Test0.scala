import scala.annotation.StaticAnnotation

class IdAnnotation extends StaticAnnotation
class LabelId (id: Int) extends IdAnnotation
class PropId (id: Int) extends IdAnnotation
class RelId (id: Int) extends IdAnnotation
class GivesLife extends IdAnnotation
class RequiresLifeId (id: Int) extends IdAnnotation
class Scale (id: Int) extends StaticAnnotation


trait Decimal
trait Index
trait Master {
    @LabelId(0x1002) def isMaster: Boolean
}

// 0x1-label, 0x2-prop, 0x3/4-old-rel, 0x5-new-test

trait Caption {
    @PropId(0x0102) def caption: String
}

trait GeoPoint {
    @PropId(0x000b) def latitude: Decimal @Scale(6)
    @PropId(0x000c) def longitude: Decimal @Scale(6)
}

@LabelId(0x1001) trait Urk

@LabelId(0x0298) trait Area extends Master with Caption {
    def areaName = caption
    @PropId(0x000a) def osmData: String
    @PropId(0x0001) def startPoint: GeoPoint
    @PropId(0x0002) def endPoint: GeoPoint
    0x014c; def zones: List[GivesLife[Zone]]
    ???
}

@LabelId(0x0154) trait Zone {
    @PropId(0x1003) def osmPoints: List[GeoPoint]
}

@LabelId(0x0354) trait StorageZone extends Zone {
    ??? //def isZone = true
    @PropId(0x01b3) def startEnterPoint: Int
    @RelId(0x1003) def urk: Urk @RequiresLife
}

@World trait MyIndexes {
    @Index def areaBySrcId: Map[SrcId,Area]
}

/*
trait-s for world
* */

/*
if we compose using 'with':
to avoid conflicts two label-class-es with common parents must not be attached to single srcId;
label split (to resolve conflicts) will require costly migration;
so the best will be either:
    1) flexible (?)
        not to inherit label-class from anything more tan system-node (srcId,labelId,etc);
        have many labels per srcId;
        use labels for optionals;
        use world.$toLabel(srcId) or world.$labelGroup(srcId).toLabel to access
    !2) faster (?)
        use single label per srcId;
        compose label-class-es in project-modules;
        inject label-class-factories to app;
        use another srcId for optional label;
        to access optional, use ref-property (typed with interface in the same module);
        and compose these interface to label-class in project-modules;

macro vs gen


*/


