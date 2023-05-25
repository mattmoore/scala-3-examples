import upickle.default.*

trait DomainError
case class LoadFailure(exception: Throwable) extends DomainError

case class Person(
    firstName: String,
    lastName: String
)

object Person {
    implicit val rw: ReadWriter[Person] = macroRW
}
