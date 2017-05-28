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

## Unfold

The inverse of a `fold` is an `unfold`. An unfold allows us to take some value and build up to a larger data structure. To achieve this we need to use a data structure that supports **lazy evaluation**, such as a `Stream`:

```scala
scala> val ones: Stream[Int] = Stream.cons(1, ones)
ones: Stream[Int] = Stream(1, ?)
```

This stream is self referencing, to create an infinite list of `1`'s:

```scala
scala> ones.take(5).toList
res0: List[Int] = List(1, 1, 1, 1, 1)
```

Due to the infinite nature of streams we can write a function `unfold` to generate a stream based around some computation `f`:

```scala
import Stream._

def unfold[A, S](z: S)(f: S => Option[(A, S)]): Stream[A] = f(z) match {
  case Some((curr, next)) => cons(curr, unfold(next)(f))
  case None => Empty
}
```

We can then generate structures from values, for example we can implement `ones` in terms of `unfold`:

```scala
def ones: Stream[Int] =
  unfold(1)(x => Some(x -> x))
```

## Catamorphism

    List(2, 2, 2, 2).fold(0)(_ + _) should be(8)
