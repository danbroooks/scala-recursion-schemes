# Scala recursion schemes

Recursion schemes explained with Scala.

## Origami

If we take a look at the native type `List`, when we have a list of type `List[Int]` we can perform the operations `sum` and `product` on that list with the following results:

```scala
scala> val twos = List(2, 2, 2, 2)
twos: List[Int] = List(2, 2, 2, 2)

scala> twos.sum
res0: Int = 8

scala> twos.product
res1: Int = 16
```

## Catamorphism

    List(2, 2, 2, 2).fold(0)(_ + _) should be(8)
