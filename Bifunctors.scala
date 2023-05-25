//> using scala "3.2.2"
//> using dep "org.typelevel::cats-core:2.9.0"

import cats.*
import cats.syntax.all.*

val records: List[(Int, Int)] = List(
    (450000, 3),
    (770000, 3),
    (990000, 2),
    (2100, 4),
    (433000, 3)
)

def calculateContributionPerMonth(balance: Int, lifetime: Int) =
    balance / lifetime

val result: List[Int] =
    records.map(
        record => record.bimap(
            cents => cents / 100,
            years => 12 * years
        )
    ).map((calculateContributionPerMonth _).tupled)


case class Person(name: String)
val people = List(
    (1, Person("Matt")),
    (2, Person("Mary"))
)

val peopleResult =
    people.map(
        person => person.bimap(
            id => s"P:$id",
            person => s"Hello ${person.name}"
        )
    )

@main
def main(args: String*) =
    println(result)
    println(peopleResult)
