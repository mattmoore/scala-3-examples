//> using scala "3.2.2"
//> using toolkit "0.1.6"

@main
def main(args: String*) =
    val data = loadEither("people.json") match
        case Right(people) =>
            println("Successfully loaded people:")
            people.foreach(println)
        case Left(error) =>
            println(error)
