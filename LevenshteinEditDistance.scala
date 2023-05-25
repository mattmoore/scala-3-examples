package cli.adapters

import scala.math.min
import scala.language.postfixOps

object Solution {
    def minDistance(word1: String, word2: String): Int = {
        levn(word1, word2)
    }

    private lazy val levn: (String, String) => Int = { (s1, s2) =>
        ((0 to s2.length).toList /: s1)((prev, x) =>
            (prev zip prev.tail zip s2).scanLeft(prev.head + 1) { case (h, ((d, v), y)) =>
                min(min(h + 1, v + 1), d + (if (x == y) 0 else 1))
            }
        ) last
    }
}
