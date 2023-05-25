import scala.util.*
import upickle.default.*

def loadThrowable(source: String): List[Person] = try {
    val json = os.read(os.pwd / os.RelPath(source))
    read[List[Person]](json)
} catch {
    throw _
}

def loadEither(source: String): Either[DomainError, List[Person]] = Try {
    val json = os.read(os.pwd / os.RelPath(source))
    read[List[Person]](json)
} match {
    case Success(value)     => Right(value)
    case Failure(exception) => Left(LoadFailure(exception))
}
