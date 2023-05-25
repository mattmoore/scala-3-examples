//> using scala "3.2.2"

// Type Class
trait Show[A] {
  extension(a: A) def show: String
}

case class Person(firstName: String, lastName: String)

// Type Class instance via given
given Show[Person] with {
  extension(p: Person) def show: String =
    s"${p.firstName} ${p.lastName}"
}

@main
def main(args: String*) = {
  val person = Person("Matt", "Moore")
  println(person.show)
}
