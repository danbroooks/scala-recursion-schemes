package recursion_schemes

import org.scalatest._

class CataSpec extends FreeSpec with Matchers {
  "simple catamorphism" in {
    List(1, 2, 4, 8).fold(0)(_ + _) should be(15)
  }
}
