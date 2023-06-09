//> using scala "3.2.2"

//> using dep "io.circe::circe-core:0.14.5"
//> using dep "io.circe::circe-generic:0.14.5"
//> using dep "io.circe::circe-parser:0.14.5"

//> using dep "org.http4s::http4s-circe:0.23.18"
//> using dep "org.http4s::http4s-dsl:0.23.18"
//> using dep "org.http4s::http4s-ember-client:0.23.18"
//> using dep "org.http4s::http4s-ember-server:0.23.18"

//> using dep "org.typelevel::cats-effect:3.4.10"

//> using dep "ch.qos.logback:logback-classic:1.4.7"

import cats.effect.*

import com.comcast.ip4s.*

import io.circe.*
import io.circe.syntax.*
import io.circe.generic.auto.*

import org.http4s.*
import org.http4s.circe.*
import org.http4s.dsl.io.*
import org.http4s.implicits.*
import org.http4s.ember.server.*

case class HelloResponse(greeting: String)

object Main extends IOApp {
  val helloWorldService = HttpRoutes.of[IO] {
    case GET -> Root / "hello" / name =>
      Ok(HelloResponse(s"Hello, $name").asJson)
  }.orNotFound

  def run(args: List[String]): IO[ExitCode] =
    EmberServerBuilder
      .default[IO]
      .withHost(ipv4"0.0.0.0")
      .withPort(port"8080")
      .withHttpApp(helloWorldService)
      .build
      .use(_ => IO.never)
      .as(ExitCode.Success)
}
