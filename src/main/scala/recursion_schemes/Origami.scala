package recursion_schemes

object Origami {

  object Naive {

    def sum(ints: List[Int]): Int = ints match {
      case Nil => 0
      case x :: xs => x + sum(xs)
    }

    def product(ints: List[Int]): Int = ints match {
      case Nil => 1
      case x :: xs => x * product(xs)
    }
  }

  object Folded {

    def sum(ints: List[Int]) =
      fold(0, ints)(_ + _)

    def product(ints: List[Int]) =
      fold(1, ints)(_ * _)

    def fold[A](a: A, xs: List[A])(f: (A, A) => A): A = xs match {
      case Nil => a
      case h :: t => f(h, fold(a, t)(f))
    }
  }
}
