//> using scala "3.2.2"

import Macro.*

def f(n: Int) = {
  Thread.sleep(500L * n)
  20
}

@main
def main =
  timed {
    println("Start2")
    Thread.sleep(1000L)
    println("End")
  }

  timed(f(1))
  timed(f(2))
