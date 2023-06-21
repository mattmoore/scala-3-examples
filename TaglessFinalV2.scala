//> using scala "3.3.0"

object TaglessFinalV2 {
    trait Algebra[E[_]] {
        def b(boolean: Boolean): E[Boolean]
        def i(int: Int): E[Int]
        def or(left: E[Boolean], right: E[Boolean]): E[Boolean]
        def and(left: E[Boolean], right: E[Boolean]): E[Boolean]
        def sum(left: E[Int], right: E[Int]): E[Int]
    }

    case class SimpleExpr[A](value: A)
    given simpleAlgebra: Algebra[SimpleExpr] with {
        override def b(boolean: Boolean) = SimpleExpr(boolean)
        override def i(int: Int) = SimpleExpr(int)
        override def or(left: SimpleExpr[Boolean], right: SimpleExpr[Boolean]) = SimpleExpr(left.value || right.value)
        override def and(left: SimpleExpr[Boolean], right: SimpleExpr[Boolean]) = SimpleExpr(left.value && right.value)
        override def sum(left: SimpleExpr[Int], right: SimpleExpr[Int]) = SimpleExpr(left.value + right.value)
    }

    def program1[E[_]](using alg: Algebra[E]): E[Boolean] = {
        import alg.*
        or(b(true), and(b(true), b(false)))
    }

    def program2[E[_]](using alg: Algebra[E]): E[Int] = {
        import alg.*
        sum(i(24), i(-3))
    }
}

def demoTagless: Unit = {
    import TaglessFinalV2.*
    println(program1[SimpleExpr])
    println(program2[SimpleExpr])
}

@main
def main =
    demoTagless
