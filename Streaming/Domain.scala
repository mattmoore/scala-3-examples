package playback.domain

trait Media {
  val id: Int
  val title: String
  val url: String
}

case class Audio(
    override val id: Int,
    override val title: String,
    override val url: String
) extends Media

case class Video(
    override val id: Int,
    override val title: String,
    override val url: String
) extends Media

case class MediaStream(
    id: Int,
    title: String,
    media: List[Media]
)
