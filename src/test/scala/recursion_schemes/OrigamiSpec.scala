package recursion_schemes

import org.scalatest._

class OrigamiSpec extends FreeSpec with Matchers {
  val twos = List(2, 2, 2, 2)

  "get the sum of our list" in {
    twos.sum should be(8)
  }

  "get the product of all elements in the lsit" in {
    twos.product should be(16)
  }
}
