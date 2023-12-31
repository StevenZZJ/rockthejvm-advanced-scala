package lectures.part1

import java.sql.Wrapper

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

  // infix patterns
  case class Or[A, B](a: A, b: B)

  val either = Or(2, "two")
  val humanDescription = either match {
    case number Or string => s"$number is written as $string"
  }
  println(humanDescription)

  // decomposing sequences
  val vararg = numbers match {
    case List(1, _*) => "starting with 1"
    case Nil =>
  }

  abstract class MyList[+A] {
    def head: A = ???

    def tail: MyList[A] = ???
  }

  case object Empty extends MyList[Nothing]

  case class Cons[+A](override val head: A, override val tail: MyList[A]) extends MyList[A]

  object MyList {
    def unapplySeq[A](list: MyList[A]): Option[Seq[A]] =
      if (list == Empty) Some(Seq.empty)
      else unapplySeq(list.tail).map(list.head +: _)
  }


  val myList: MyList[Int] = Cons(1, Cons(2, Empty))
  val decomposed = myList match {
    case MyList(1, 2, _*) => "starting with 1, 2"
    case _ =>
  }

  // custom return types for unapply
  // isEmpty: Boolean, get: something
  abstract class Wrapper[T] {
    def isEmpty: Boolean
    def get: T
  }

  object PersonWrapper {
    def unapply(person: Person): Wrapper[String] = new Wrapper[String] {
      def isEmpty = false
      def get: String = person.name
    }
  }

  println(bob match {
    case PersonWrapper(n) => s"This person's name is $n"
    case _ => "An alien"
  })






}



