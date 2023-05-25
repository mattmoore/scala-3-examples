object Logarithms:
    opaque type Logarithm = Double

    object Logarithm:
        def apply(d: Double) = math.log(d)

    extension (x: Logarithm)
        def toDouble: Double = math.exp(x)
        def +(y: Logarithm): Logarithm = Logarithm(math.exp(x) + math.exp(y))
        def *(y: Logarithm): Logarithm = x + y

@main
def main(args: String*) =
    import Logarithms.*
    val log2 = Logarithm(2.0)
    val log3 = Logarithm(3.0)
    println((log2 * log3).toDouble)
    println((log2 + log3).toDouble)

    val d: Double = log2
