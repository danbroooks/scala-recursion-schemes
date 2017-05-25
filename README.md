# Scala recursion schemes

Recursion schemes explained with Scala.

## Catamorphism

    List(1, 2, 4, 8).fold(0)(_ + _) should be(15)
