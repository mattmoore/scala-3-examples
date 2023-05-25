//> using scala "3.2.2"

import tabledsl.*

val t = table { ($t: Table) ?=>
  row { ($r: Row) ?=>
    cell("top left")(using $r)
    cell("top right")(using $r)
  }(using $t)

  row { ($r: Row) ?=>
    cell("bottom left")(using $r)
    cell("bottom right")(using $r)
  }(using $t)
}

@main
def main =
  println(t)
