//> using scala "3.2.2"
//> using dep "org.typelevel::shapeless3-deriving:3.3.0"

import shapeless3.deriving.*

// Type class definition, eg. from Cats
trait Monoid[A]:
  def empty: A
  def combine(x: A, y: A): A
  extension (x: A) def |+| (y: A): A = combine(x, y)

object Monoid:
  given Monoid[Unit] with
    def empty: Unit = ()
    def combine(x: Unit, y: Unit): Unit = ()

  given Monoid[Boolean] with
    def empty: Boolean = false
    def combine(x: Boolean, y: Boolean): Boolean = x || y

  given Monoid[Int] with
    def empty: Int = 0
    def combine(x: Int, y: Int): Int = x+y

  given Monoid[String] with
    def empty: String = ""
    def combine(x: String, y: String): String = x+y

  given monoidGen[A](using inst: K0.ProductInstances[Monoid, A]): Monoid[A] with
    def empty: A =
      inst.construct([t] => (ma: Monoid[t]) => ma.empty)
    def combine(x: A, y: A): A =
      inst.map2(x, y)([t] => (mt: Monoid[t], t0: t, t1: t) => mt.combine(t0, t1))

  inline def derived[A](using gen: K0.ProductGeneric[A]): Monoid[A] = monoidGen

// ADT definition
case class ISB(i: Int, s: String, b: Boolean) derives Monoid
val a = ISB(23, "foo", true)
val b = ISB(13, "bar", false)

val c = a |+| b // == ISB(36, "foobar", true)

@main
def main() =
    println(c)