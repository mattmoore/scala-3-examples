//> using scala "3.2.2"
//> using dep "org.typelevel::cats-core:2.9.0"

import cats.*
import cats.syntax.all.*

@main
def main(args: String*) =
    println("Semigroup with Option - cannot combine with None")
    println(Option(1) |+| Option(2))
    println(Option(1) |+| Option(2))

    println("SemigroupK with Option - can combine with None")
    println(Option(1) <+> Option(2))
    println(None <+> Option(2))
    println(Option(1) <+> Option(2))
    println(None <+> None)
