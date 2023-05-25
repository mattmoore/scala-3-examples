//> using scala "3.2.2"

trait PeopleService {
    def get(id: Int): String
}

def peopleServiceFunction: PeopleService = new PeopleService {
    override def get(id: Int): String =
        "PeopleServiceFunction Matt"

    def get2(id: Int): String =
        "PeopleServiceFunction Mary"
}

class PeopleServiceClass extends PeopleService {
    override def get(id: Int): String =
        "PeopleServiceClass Matt"

    def get2(id: Int): String =
        "PeopleServiceClass Mary"
}

@main
def main() =
    val peopleServ = peopleServiceFunction
    println(peopleServ.get(1))
    val peopleServClass = new PeopleServiceClass
    println(peopleServClass.get2(1))
