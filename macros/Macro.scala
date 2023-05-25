import scala.quoted.*

object Macro:
  inline def timed[T](inline expr: T): T = ${ timedImpl('expr) }

  private def timedImpl[T: Type](expr: Expr[T])(using Quotes): Expr[T] =
    '{
      val start = System.currentTimeMillis()
      try $expr
      finally {
        val end = System.currentTimeMillis()

        // val exprAsString = ${ Expr(expr.show) }
        // println(s"Evaluating $exprAsString took: ${end - start}ms")

        val exprAsString = ${ Expr(exprAsCompactString(expr)) }.replaceAll("\\s+", " ").trim()
        val exprAsStringShort = if (exprAsString.length > 50) exprAsString.take(50) + "..." else exprAsString
        println(s"Evaluating $exprAsStringShort took: ${end - start}ms")
      }
    }

  private def exprAsCompactString[T: Type](expr: Expr[T])(using Quotes): String =
    import quotes.reflect.*
    expr.asTerm match {
      case Inlined(_, _, Apply(method, params)) => s"${method.symbol.name}(${params.map(_.show).mkString(",")})"
      case _                                    => expr.show
    }
