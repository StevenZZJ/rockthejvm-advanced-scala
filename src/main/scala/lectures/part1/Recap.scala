package lectures.part1

import scala.annotation.tailrec

object Recap {

  val aCondition: Boolean = false
  val aConditionVal = if (aCondition) 10 else 11

  // instructions vs expressions
  val aCodeBlock = {
    if (aCondition) 11
    12
  } // result of this code block is 12 as it's the last expression of the block


  // Unit equivalent of Void
  val theUnit: Unit = println("hello")

  // recursion: stack and tail
  @tailrec
  def factorial(n: Int, acc: Int): Int =
    if (n <= 0) acc
    else factorial(n - 1, n * acc)


  // OOP

  class Animal

  class Dog extends Animal

  val aDog: Animal = new Dog // subtyping polymorphism

  trait Carnivore {
    def eat(a: Animal): Unit
  }

  class Crocodile extends Animal with Carnivore {
    override def eat(a: Animal): Unit = println("crunch!")
  }


  // method notations
  val aCroc = new Crocodile
  aCroc.eat(aDog)
  aCroc eat aDog

  1 + 2
  1.+(2)

  // anonymous classes
  val aCarnivore = new Carnivore {
    override def eat(a: Animal): Unit = println("roar!")
  }

  //

  abstract class MyList[+A] // variance and variance problems more after

  // singletons and companions
  object MyList

  // case classes
  case class Person(name: String, age: Int) // serializable and with apply

  // exceptions
  val throwsException = throw new RuntimeException // Nothing


  // functional programming
  val incrementer = new Function[Int, Int] {
    override def apply(v1: Int): Int = v1 + 1
  }

  incrementer(1)

  val anonymousIncrementer: Int => Int = (x: Int) => x + 1
  List(1, 2, 3).map(anonymousIncrementer) //HOF - map, flatMap, filter - higher order function

  // for-comprehension
  val pairs = for {
    num <- List(1, 2, 3)
    char <- List('a', 'b', 'c')
  } yield num + "-" + char


  // Scala collections: Seqs, Arrays, Lists, Vectors, Maps, Tuples
  val aMap = Map(
    "a" -> 123,
    "b" -> 456
  )

  // collections: Options, Try
  val anOption: Option[Int] = Some(2)


  // pattern matching
  val x = 2
  val order = x match {
    case 1 => "first"
    case 2 => "second"
    case _ => x + "th"
  }

  val bob = Person("Bob", 22)
  val greeting = bob match {
    case Person(n, _) => s"Hi, my name is $n"
  }

  // all the patterns
  //...

}
