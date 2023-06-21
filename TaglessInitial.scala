//> using scala "3.3.0"

object TaglessInitial {
    trait Expr[A]
    case class B(boolean: Boolean) extends Expr[Boolean]
    case class Or(left: Expr[Boolean], right: Expr[Boolean]) extends Expr[Boolean]
    case class And(left: Expr[Boolean], right: Expr[Boolean]) extends Expr[Boolean]
    case class Not(expr: Expr[Boolean]) extends Expr[Boolean]
    case class I(int: Int) extends Expr[Int]
    case class Sum(left: Expr[Int], right: Expr[Int]) extends Expr[Int]

    def eval[A](expr: Expr[A]): A = expr match {
        case B(b) => b
        case I(i) => i
        case Or(left, right) => eval(left) || eval(right)
        case Sum(left, right) => eval(left) + eval(right)
    }
}

def demoTagless: Unit = {
    import TaglessInitial.*
    println(eval(Or(B(true), And(B(true), B(false)))))
    println(eval(Sum(I(24), I(-3))))
}

@main
def main =
    demoTagless
