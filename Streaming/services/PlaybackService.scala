package playback.services

import playback.*
import domain.*
import repositories.*

trait PlaybackService {
  def tuneSource(id: Int): PlaybackResponse
  def peek(id: Int, context: List[Media]): PlaybackResponse
  def skip(mediaStream: MediaStream, position: Int): Unit
  def previous(mediaStream: MediaStream, position: Int): Unit
}

case class PlaybackResponse(media: List[Media])

def playbackService(using repo: MediaSourceRepository) = new PlaybackService {
  override def tuneSource(id: Int): PlaybackResponse =
    val db = repo.getByArtist(id)
    PlaybackResponse(db.take(3))

  override def peek(id: Int, context: List[Media]): PlaybackResponse =
    val db = repo.getByArtist(id)
    val next = db.filterNot(context => context.contains)
    PlaybackResponse(next)

  override def skip(mediaStream: MediaStream, position: Int): Unit = ???

  override def previous(mediaStream: MediaStream, position: Int): Unit = ???
}
