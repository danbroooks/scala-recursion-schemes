package recursion_schemes

import org.scalatest._

class OrigamiSpec extends FreeSpec with Matchers {
  val twos = List(2, 2, 2, 2)

  "without fold" - {
    import Origami.Naive._

    "get the sum of our list" in {
      sum(twos) should be(8)
    }

    "get the product of all elements in the lsit" in {
      product(twos) should be(16)
    }
  }

  "using fold" - {
    import Origami.Folded._

    "get the sum of our list" in {
      sum(twos) should be(8)
    }

    "get the product of all elements in the lsit" in {
      product(twos) should be(16)
    }
  }
}
