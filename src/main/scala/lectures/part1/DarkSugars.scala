package lectures.part1

import jdk.jshell.spi.ExecutionControl.RunException

import java.lang
import scala.util.Try

object DarkSugars extends App { // extend App to be able to run the code

  // #1: Methods with single param
  def singleArgMethod(arg: Int): String = s"$arg little ducks..."

  val description = singleArgMethod {
    //write some code
    10
  }

  val aTryInstance = Try { // similar to Java {...}
    throw new lang.RuntimeException // the apply method of try with single argument
  }

  List(1, 2, 3).map { x =>
    x + 1
  }


  // syntax sugar #2: single abstract method
  trait Action {
    def act(x: Int): Int
  }

  val anInstance: Action = new Action {
    override def act(x: Int): Int = x + 1
  }

  val aFunkyInstance: Action = (x: Int) => x + 1 //magic

  // ex: runnable
  val aThread = new Thread(new Runnable {
    override def run(): Unit = println("hello")
  })

  val aSweeterThread = new Thread(() => println("hello2"))

  abstract class AnAbstractType {
    def implemented: Int = 23
    def f(a: Int): Unit
  }

  val anAbstractInstance: AnAbstractType = (a: Int) => println("sweet")

  // #3: :: and #::
  val prependedList = 2 :: List(3,4)
  // actually: List(3,4).::(2)
  //in scala: Last character decides associativity of method

  class MyStream[T] {
    def -->:(value: T): MyStream[T] = this
  }

  val myStream = 1 -->: 2 -->: new MyStream[Int]


  // #4: multi-word method naming
  class  TeanGirl(name: String) {
    def `and then said`(gossip: String): Unit = println(s"$name said $gossip")
  }

  val lilly = new TeanGirl("Lilly")
  lilly `and then said` "scala is sweet"


  // #5: infix types
  class Composite[A, B]
  val composite: Composite[Int, String] = ???
  val composite2: Int Composite String = ???

  class -->[A,B]
  val towards: Int --> String = ???


  // #6: update()
  val  anArray = Array(1,2,3)
  anArray(2) = 7 // rewritten to anArray.update(2, 7) by the compiler
  //used in mutable collections

  // #7: setters for mutable containers
  class Mutable{
    private var internalMember: Int = 0 // private for OO encapsulation

    def member: Int = internalMember // "getter"
    def member_=(value: Int): Unit = {
      internalMember = value // "setter"
    }
  }

  val aMutableContainer = new Mutable
  aMutableContainer.member = 10 // rewritten as aMutableContainer.member_=(42)


}
