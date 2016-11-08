
package test0

import schema._

@Schema object Test0 {
  @Id(0x0001) trait ModelA {
    def fieldA: Int
  }
  @Id(0x0001) trait ModelB {
    def fieldB: ModelC
  }
  @Id(0x0001) trait ModelC extends ModelA with ModelB {
    def fieldC: ModelB
  }



  //@Id(0x0001) class ModelC { }

}
