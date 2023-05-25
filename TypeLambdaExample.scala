//> using scala "3.2.2"

@main
def main(args: String*) = {
    println("Successes")
    println(returnSuccess)
    println(returnSuccess2)
    println(returnSuccess3)
    println(returnSuccess4)

    println("Failures")
    println(returnFailure)
    println(returnFailure2)
    println(returnFailure3)
    println(returnFailure4)
}

// Let's define a typical domain error ADT:
trait DomainError
case class LoadFailure() extends DomainError

// Here we're using Either[DomainError, List[Int]]
def returnFailure: Either[DomainError, List[Int]] =
    Left(LoadFailure())

def returnSuccess: Either[DomainError, List[Int]] =
    Right(List(1, 2, 3))

// Now let's define a type lambda:
//      ListEither[DomainError, Int]
// which allows for this alternate syntax:
//          Either[DomainError, List[Int]]
type ListEither = [E, A] =>> Either[E, List[A]]

// And let's define functions similar to the ones above, using the type lambda we defined
def returnFailure2: ListEither[DomainError, Int] =
    Left(LoadFailure())

def returnSuccess2: ListEither[DomainError, Int] =
    Right(List(1, 2, 3))

// Not much saved there, but what if we had a set of Eithers wrapped in an Either?
def returnSuccess3: Either[DomainError, List[Either[DomainError, Int]]] =
    Right(
        List(
            Right(1),
            Right(2),
            Right(3)
        )
    )

def returnFailure3: Either[DomainError, List[Either[DomainError, Int]]] =
    Right(
        List(
            Left(LoadFailure()),
            Left(LoadFailure()),
            Left(LoadFailure())
        )
    )

// That function signature is getting quite long. We can use type lambdas to shorten this:
//      Either[DomainError, List[Either[DomainError, Int]]]
// to this:
//      NestedListEither[DomainError, Int]
type NestedListEither = [E, A] =>> Either[DomainError, List[Either[DomainError, Int]]]

// Now let's rewrite those functions with this new type lambda:
def returnSuccess4: NestedListEither[DomainError, Int] = // using our new type lambda
    Right(
        List(
            Right(1),
            Right(2),
            Right(3)
        )
    )

def returnFailure4: NestedListEither[DomainError, Int] = // using our new type lambda
    Right(
        List(
            Left(LoadFailure()),
            Left(LoadFailure()),
            Left(LoadFailure())
        )
    )

// That made for a much simpler signature that's easier to read at a glance