//> using scala "3.2.2"

import scala.deriving.Mirror
import scala.compiletime.constValue
import scala.compiletime.erasedValue
import scala.compiletime.summonInline
import scala.compiletime.summonAll

trait Showable[T]:
  def show(t: T): String

object Showable:
  given showable[T]: Showable[T] = new Showable[T]:
    def show(t: T): String = t.toString

  inline def derived[T](using m: Mirror.Of[T]): Showable[T] =
    inline m match {
      case s: Mirror.SumOf[T] => ???
      case p: Mirror.ProductOf[T] =>
        val showElems: List[Showable[_]] = summonShowAll[m.MirroredElemTypes]
        showProduct(p, showElems)
    }

  private inline def summonShowAll[T <: Tuple]: List[Showable[_]] =
    inline erasedValue[T] match {
      case _: EmptyTuple => Nil
      case _: (t *: ts) => summonInline[Showable[T]] :: summonShowAll[ts]
    }

  private inline def showProduct[T](p: Mirror.ProductOf[T], showElems: List[Showable[_]]): Showable[T] =
    val label = constValue[p.MirroredLabel]
    val elemLabels = labelsToList[p.MirroredElemLabels]
    new Showable[T]:
      def show(t: T): String =
        val elemValues = t.asInstanceOf[Product].productIterator.toList
        val elems = elemLabels.zip(elemValues).zip(showElems).map {
          case ((elLabel, el), safeShow) =>
            val shown = if (isSensitive(elLabel)) "***" else {
              safeShow.asInstanceOf[Showable[Any]].show(el)
            }
            s"$elLabel = $shown"
        }.mkString(", ")
        s"$label($elems)"
  
  private inline def labelsToList[T <: Tuple]: List[String] =
    inline erasedValue[T] match {
      case _: EmptyTuple => Nil
      case _: (t *: ts) => constValue[t].toString :: labelsToList[ts]
    }
  
  private def isSensitive(n: String): Boolean =
    val nn = n.toLowerCase
    nn.contains("token") || nn.contains("apikey") || nn.contains("password")

extension[T](t:T) {
  def show(using Showable[T]): String = summon[Showable[T]].show(t)
}

case class Person(firstName: String, lastName: String) derives Showable
case class Company(name: String, employeeCount: Int) derives Showable

@main
def main(args: String*) = {
  println(Person("Matt", "Moore").show)
  println(Company("Xebia", 4000).show)
}
