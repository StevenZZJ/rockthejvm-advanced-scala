package lectures.part1

object PatternMatching extends App {

  val numbers = List(1)
  val description = numbers match {
    case head :: Nil => println(s"only head")
    case _ =>
  }
  /*
   pattern matching can do
   - constants
   - wildcards
   - case classes
   - tuples
   - some special magic like above
  */


  class Person(val name: String, val age: Int)

  object Person { // this name can be named otherwise but it's better to be a companion object
    def unapply(person: Person): Option[(String, Int)] = {
      if (person.age < 20) None
      else Some((person.name, person.age))
    }

    def unapply(age: Int): Option[String] =
      Some(if (age < 20) "minor" else "major")

  }

  val bob = new Person("Bob", 10)
  val greeting = bob match {
    case Person(n, a) => s"Hi, I'm $n, I'm $a yo"
    case _ =>
  }

  println(greeting)

  val legalStatus = bob.age match {
    case Person(status) => s"LS is $status"
  }

  println(legalStatus)

  /*
  Exercise
  */

  val n: Int = 45
  val mathProperty = n match {
    case x if x < 10 => "single digit"
    case x if x % 2 == 0 => "even number"
    case _ => "no property"
  }

  object IntPattern {
    def unapply(i: Int): Option[String] = {
      if (i < 10) Some("single digit")
      else if (i % 2 == 0) Some("even number")
      else Some("no property")
    }
  }

  // answer
  object even {
    def unapply(arg: Int): Boolean =
      arg % 2 == 0
  }

  object singleDigit {
    def unapply(arg: Int): Boolean =
      arg > -10 && arg < 10
  }

  val n2: Int = 45
  val mathProperty2 = n2 match {
    case singleDigit() => "single digit" // no _ in () the compiler will translate to a simple Boolean test
    case even() => "even number"
    case _ => "no property"
  }

}



