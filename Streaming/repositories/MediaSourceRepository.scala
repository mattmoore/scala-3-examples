package playback.repositories

import playback.domain.*

trait MediaSourceRepository {
  def getByArtist(id: Int): List[Media]
}

def mediaSourceRepository = new MediaSourceRepository {
  override def getByArtist(id: Int): List[Media] = List(
    Audio(id = 1, "Sandman", url = "/media/metallica/1"),
    Audio(id = 2, "Call of Ktulu", url = "/media/metallica/2"),
    Audio(id = 3, "Orion", url = "/media/metallica/3"),
    Audio(id = 4, "Another Song", url = "/media/metallica/4"),
    Audio(id = 5, "Yet Another Song", url = "/media/metallica/5"),
  )
}
