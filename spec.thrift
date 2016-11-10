/*
struct Tweet {

    0x001: required i32 userId;
    2: required string userName;
    3: required string text;
    //4: optional Location loc;
    //5: optional TweetType tweetType = TweetType.TWEET // 5
    16: optional string language = "english"
}

struct SrcId {}

struct Test {
    0x0022: bool label,
    0o8:
    0x2: list<Tweet> tweets (ref = "1"),
}

service Abc extends Def {
    Test transform()(a="9")

}(a="9")


struct Master {}

? give require
? userLable
? startPoint indexer
? mayBe -- label group (Urk)
*/

struct Decimal {}
typedef i32 Index
struct Master {
    0x5002: bool label
}

// 0x1-label, 0x2-prop, 0x3/4-old-rel, 0x5-new-test

const i32 captionAttrId = 0x00102;

struct Caption {
    0x2102: string apply() //value?
}

struct GeoPoint {
    0x200b: Decimal latitude (scale="6")
    0x200c: Decimal longitude (scale="6")
}

struct Urk {
    0x5001: bool label
}

struct Area {
    0x1298: bool label
    required Master asMaster
    Caption asAreaName
    0x200a: string osmData
    0x2001: GeoPoint startPoint
    0x2002: GeoPoint endPoint
    0x314c: list<Zone> zones (life="G")

    ...

}

struct Zone {
    0x1154: bool label
    0x3003: list<GeoPoint> osmPoints
    ...
}

struct StorageZone {
    0x1354: bool label
    required Zone asZone
    0x21b3: Index startEnterPoint
    Urk urk (life="R")
}

