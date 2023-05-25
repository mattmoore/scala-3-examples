//> using scala "3.2.2"
//> using toolkit "0.1.6"

@main
def main(args: String*) =
    val db: DB = new DB {
        override def get(k: Key): Option[k.Value] =
            // k match {
            //     case value: String => Option(Name("String Value"))
            //     case value: Int => Option(Age(1))
            // }
            Option(k.Value("String value"))
    }
    val res1: Option[String] = db.get(Name)
    val res2: Option[Int] = db.get(Age)
    println(res1)
    println(res2)

trait Key { type Value }
trait DB {
    def get(k: Key): Option[k.Value]
}

case class Name(value: String) extends Key {
    type Value = String
}

object Age extends Key { type Value = Int }