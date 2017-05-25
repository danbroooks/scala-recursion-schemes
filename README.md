# Scala recursion schemes

Recursion schemes explained with Scala.

## Origami

Given a list of type `List[Int]` we can perform the operations `sum` and `product` on that list with the following results:

```scala
scala> val twos = List(2, 2, 2, 2)
twos: List[Int] = List(2, 2, 2, 2)

scala> twos.sum
res0: Int = 8

scala> twos.product
res1: Int = 16
```

If these functions weren't available to us natively, how would we implement these ourselves?

```scala
def sum(ints: List[Int]) = ints match {
  case Nil => 0
  case x :: xs => x + sum(xs)
}

def product(ints: List[Int]) = ints match {
  case Nil => 1
  case 0 :: _ => 0
  case x :: xs => x * product(xs)
}
```

Here is where we can make our first generalisation, both of these functions share a common pattern, they have both have some initial base case and then some recursive computation to operate on structures bigger than the `Nil` case. We can abstract this pattern into a function `fold`:

```scala
def fold[A](a: A, xs: List[A])(f: (A, A) => A): A = xs match {
  case Nil => a
  case h :: t => f(h, fold(a, t)(f))
}
```

We can then rewrite `sum` and `product` in terms of `fold`:

```scala
def sum(ints: List[Int]) =
  fold(0, ints)(_ + _)

def product(ints: List[Int]) =
  fold(1, ints)(_ * _)
```

## Catamorphism

    List(2, 2, 2, 2).fold(0)(_ + _) should be(8)
