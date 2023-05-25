//> using scala "3.2.2"

def testMatches(list: List[Int]): String =
    list match
        case Nil => "Empty list"
        case head +: Nil  => "Has one item"
        case _ :+ tail    => s"Last item is $tail"
        case head :: next => "Full list"
    

@main
def main(args: String*) = {
    List(
        testMatches(List()),
        testMatches(List(1)),
        testMatches(List(1, 2))
    ).foreach(println)
}
