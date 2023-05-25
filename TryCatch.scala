//> using scala "3.2.2"
//> using toolkit "0.1.6"

@main
def main(args: String*) = try {
    val people = loadThrowable("people.json")
    people.foreach(println)
} catch { error =>
    println(error)
}
