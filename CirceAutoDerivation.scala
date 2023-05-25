//> using scala "3.2.2"
//> using dep "io.circe::circe-core:0.14.5"
//> using dep "io.circe::circe-generic:0.14.5"
//> using dep "io.circe::circe-parser:0.14.5"

import io.circe.*
import io.circe.syntax.*
import io.circe.generic.auto.*

case class User(id: Int, firstName: String, lastName: String)

@main
def main() =
  // Successful conversion to JSON
  println {
    User(1, "Matt", "Moore").asJson
  }

  // Successful JSON parse
  println {
    val json =
      """{
            "id": 1,
            "firstName": "Matt",
            "lastName": "Moore"
          }"""
    parser.decode[User](json)
  }

  // Invalid JSON for the User type
  println {
    val json = """{"greeting": "Hello, Matt"}"""
    parser.decode[User](json)
  }
