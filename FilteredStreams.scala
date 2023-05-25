//> using scala "3.2.2"
//> using dep "org.typelevel::cats-effect:3.4.9"
//> using dep "co.fs2::fs2-core:3.6.1"

import cats.effect.IO
import fs2.{Pure, Stream}

case class MediaSource(id: Int, title: String)
case class ThumbsDown(mediaSourceId: Int)

val sequence1 = Stream[Pure, MediaSource](
    MediaSource(1, "Howard Stern"),
    MediaSource(2, "Golden Oldies"),
    MediaSource(3, "Metallica"),
    MediaSource(1, "Howard Stern")
)

val votes = Set(
    ThumbsDown(mediaSourceId = 1)
)

@main
def main() = {
    sequence1
        .filter(mediaSource => !votes.map(_.mediaSourceId).contains(mediaSource.id))
        .map(_.title)
        .map(println)
        .compile
        .drain
}
