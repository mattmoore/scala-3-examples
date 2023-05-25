//> using scala "3.2.2"
//> using dep "org.typelevel::shapeless3-deriving:3.3.0"

import shapeless3.deriving.*

trait Show[A]:
    extension (a: A)
        def show: String

object Show:
    given Show[Unit] with
        extension (a: Unit)
            def show: String = a.toString

    given Show[Boolean] with
        extension (a: Boolean)
            def show: String = a.toString
    
    given Show[Int] with
        extension (a: Int)
            def show: String = a.toString

    given Show[String] with
        extension (a: String)
            def show: String = identity(a)

    given showGen[A](using inst: K0.ProductInstances[Show, A]): Show[A] with
        def show: A =
            inst.unfold[A]([t] => (sh: Show[t]) => sh.show)

    inline def derived[A](using gen: K0.ProductGeneric[A]): Show[A] =
        showGen

case class Person(name: String, age: Int) derives Show

@main
def main =
    val p = Person("Matt", 36)
    println(p.show)
