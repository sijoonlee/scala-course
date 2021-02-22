package lecture.part2OOP

object Generics extends App{

  class MyList[+A]{
    // trait is possible instead of class
    //use type A inside of brackets

    // def add(element: A): MyList[A] = ??? --> will show error

    def add[B >: A] (element: B): MyList[B] = ???
    /* A=Cat, B=Animal
    * B is super type of A
    * */

  }

  class MyMap[Key, Value]

  val listOfIntegers = new MyList[Int]
  val listOfStrings = new MyList[String]

  object MyList{
    def empty[A]: MyList[A] = ???
  }
  // val emptyListOfIntegers = MyList.empty[Int]

  // variance problem : if B extends A, should List[B] extends List[A]?
  class Animal
  class Cat extends Animal
  class Dog extends Animal

  // 1. Yes, List[Cat] extends List[Animal] => Covariance
  class CovariantList[+A]

  val animal:Animal = new Cat
  val animalList: CovariantList[Animal] = new CovariantList[Cat]

  // animalList.add(new Dog)  What will happen? Hard question -> it returns list of Animals

  // 2. No - Invariance
  class InvarianceList[A]
  // below is not working
  // val invarianceList: InvarianceList[Animal] = new InvarianceList[Cat]
  // below is working
  val invarianceList: InvarianceList[Animal] = new InvarianceList[Animal]

  // 3. Hell, no! - the opposite Contravariance
  class ContravarianceList[-A]
  val contravarianceList: ContravarianceList[Cat] = new ContravarianceList[Animal]

  class Trainer[-A]
  val trainer: Trainer[Cat] = new Trainer[Animal]

  // bounded types
  class Cage[A <: Animal](animal: A) // only accepts subtypes of Animal
  val cage = new Cage(new Dog)

  class Car
  // val cage2 = new Cage(new Car) // no error message, but not running

  // Expand MyList to be Generic

  abstract class MyListGeneric[+A] {
    def head:A
    def tail: MyListGeneric[A]
    def isEmpty: Boolean
    def add[B >: A](element: B): MyListGeneric[B]
    def printElements: String

    override def toString: String = "[" + printElements + "]"
  }

  object Empty extends MyListGeneric[Nothing]{
    override def head: Nothing = throw new NoSuchElementException

    override def tail: MyListGeneric[Nothing] = throw new NoSuchElementException

    override def isEmpty: Boolean = true

    override def add[B >: Nothing](element: B): MyListGeneric[B] = new Cons(element, Empty)

    override def printElements: String = ""
  }

  class Cons[+A](h:A, t:MyListGeneric[A]) extends MyListGeneric[A] {
    override def head: A = h

    override def tail: MyListGeneric[A] = t

    override def isEmpty: Boolean = false

    override def add[B >: A](element: B): MyListGeneric[B] = new Cons(element, this)

    override def printElements: String =
      if(t.isEmpty) "" + h
      else h + " " + t.printElements
  }

  val listOfInt: MyListGeneric[Int] = new Cons(1, new Cons(2, new Cons(3, Empty)))
  val listOfStr: MyListGeneric[String] = new Cons("a", new Cons("b", Empty))

  println(listOfInt.toString)
  println(listOfStr.toString)


}
