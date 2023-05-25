//> using scala "3.2.2"

def hello(using String) =
    s"Hello, ${summon[String]}"

@main
def main() =
    given name: String = "Matt"
    println(hello)