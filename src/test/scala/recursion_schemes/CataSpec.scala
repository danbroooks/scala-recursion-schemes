package recursion_schemes

import org.scalatest._
import matryoshka._
import matryoshka.implicits._
import matryoshka.data.Mu

sealed abstract class Expr[A]
final case class Num[A](value: Long) extends Expr[A]
final case class Mul[A](l: A, r: A)  extends Expr[A]

class CataSpec extends FreeSpec with Matchers {
  "simple catamorphism" in {
    List(1, 2, 4, 8).fold(0)(_ + _) should be(15)
  }

  "" in {
    implicit val exprFunctor = new scalaz.Functor[Expr] {
      override def map[A, B](fa: Expr[A])(f: (A) => B) = fa match{
        case Num(value) => Num[B](value)
        case Mul(l, r) => Mul(f(l), f(r))
      }
    }

    val eval: Algebra[Expr, Long] = {
      case Num(x)    => x
      case Mul(x, y) => x + y
    }

    val uneval: Coalgebra[Expr, Long] = {
      case x => Num(x)
    }

    def someExpr[T](implicit T: Corecursive.Aux[T, Expr]): T =
      Mul(Num[T](2).embed, Mul(Num[T](3).embed,
          Num[T](4).embed).embed).embed

    // someExpr[Mu[Expr]].ana(9) should be(9)

    9L.ana.apply(uneval) should be(9L)
  }
}
