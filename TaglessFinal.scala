//> using scala "3.3.0"

object TaglessFinal {
    trait Expr[A] {
        val value: A
    }

    def b(boolean: Boolean): Expr[Boolean] = new Expr[Boolean] {
        val value = boolean
    }

    def i(int: Int): Expr[Int] = new Expr[Int] {
        val value = int
    }

    def or(left: Expr[Boolean], right: Expr[Boolean]) = new Expr[Boolean] {
        val value = left.value || right.value
    }

    def and(left: Expr[Boolean], right: Expr[Boolean]) = new Expr[Boolean] {
        val value = left.value && right.value
    }

    def sum(left: Expr[Int], right: Expr[Int]) = new Expr[Int] {
        val value = left.value + right.value
    }

    def eval[A](expr: Expr[A]): A = expr.value
}

def demoTagless: Unit = {
    import TaglessFinal.*
    println(eval(or(b(true), and(b(true), b(false)))))
    println(eval(sum(i(24), i(-3))))
}

@main
def main =
    demoTagless
