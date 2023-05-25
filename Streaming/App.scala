//> using scala "3.2.2"

import playback.*
import repositories.*
import services.*

given MediaSourceRepository = mediaSourceRepository
val playbackServ = playbackService

@main
def main(args: String*) =
  val response = playbackServ.tuneSource(1)
  println(response)
  println(playbackServ.peek(4, response.media))
