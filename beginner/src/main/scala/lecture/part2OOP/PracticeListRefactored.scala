package lecture.part2OOP

object PracticeListRefactored extends App{

  /**
   *  1. Generic Trait MyPredicate[-T] with a little method test[T] => Boolean
   *  2. Generic Trait MyTransformer[-A,B] with a method transform(A) => B
   *  3. MyList:
   *      - map(transformer) => MyList
   *      - filter(predicate) => MyList
   *      - flatMap(transformer from A to MyList[B] => MyList[B]
   *
   *      class EvenPredicate extends MyPredicate[Int]
   *      class StringToIntTransformer extends MyTransformer[String, Int]
   *
   *      [ 1, 2, 3 ].map( n * 2 )
   *        = new Cons(2, [2, 3].map( n * 2 ))
   *        = new Cons(2, new Cons(4, [3].map( n * 2 )))
   *        = new Cons(2, new Cons(4, new Cons(6, Empty.map( n * 2 ))))
   *        = new Cons(2, new Cons(4, new Cons(6, Empty)))
   *        = [ 2, 4, 6 ]
   *
   *      [ 1, 2, 3 ].filter( n % 2 == 0)
   *        = [ 2, 3 ].filter( n % 2 == 0)
   *        = new Cons(2, [3].filter( n % 2 == 0)
   *        = new Cons(2, Empty.filter( n % 2 == 0))
   *        = new Cons(2, Empty)
   *        = [ 2 ]
   *
   *      [ 1, 2, 3 ].flatMap( n => [ n, n+1 ] )
   *        = [ 1, 2 ] ++ [ 2, 3 ].flatMap( n => [n, n+1] )
   *        = [ 1, 2 ] ++ [ 2, 3 ] ++ [ 3 ].flatMap( n => [n, n+1] )
   *        = [ 1, 2 ] ++ [ 2, 3 ] ++ [ 3, 4 ] ++ Empty.flatMap( n => [n, n+1])
   *        = [ 1, 2 ] ++ [ 2, 3 ] ++ [ 3, 4 ] ++ Empty
   *        = [ 1, 2, 2, 3, 3, 4 ]
   */

  abstract class MyListGeneric[+A] {
    def head:A
    def tail: MyListGeneric[A]
    def isEmpty: Boolean
    def add[B >: A](element: B): MyListGeneric[B]
    def printElements: String

    override def toString: String = "[" + printElements + "]"

    // higher order functions - receive or return function
    def map[B](transformer: A => B): MyListGeneric[B]
    def filter(predicate: A => Boolean): MyListGeneric[A]
    def flatMap[B](transformer: A => MyListGeneric[B]): MyListGeneric[B]

    def ++[B >: A](list: MyListGeneric[B]): MyListGeneric[B]
  }

  case object Empty extends MyListGeneric[Nothing]{
    override def head: Nothing = throw new NoSuchElementException
    override def tail: MyListGeneric[Nothing] = throw new NoSuchElementException
    override def isEmpty: Boolean = true
    override def add[B >: Nothing](element: B): MyListGeneric[B] = new Cons(element, Empty)
    override def printElements: String = ""
    def map[B](transformer: Nothing => B) : MyListGeneric[B] = Empty
    def filter(predicate: Nothing => Boolean): MyListGeneric[Nothing] = Empty
    def ++[B >: Nothing](list: MyListGeneric[B]): MyListGeneric[B] = list
    def flatMap[B](transformer: Nothing => MyListGeneric[B]): MyListGeneric[B] = Empty

  }

  case class Cons[+A](h:A, t:MyListGeneric[A]) extends MyListGeneric[A] {
    override def head: A = h
    override def tail: MyListGeneric[A] = t
    override def isEmpty: Boolean = false
    override def add[B >: A](element: B): MyListGeneric[B] = new Cons(element, this)
    override def printElements: String =
      if(t.isEmpty) "" + h
      else h + " " + t.printElements
    def map[B](transformer: A => B) : MyListGeneric[B] =
      new Cons(transformer(h), t.map(transformer))
    def filter(predicate: A => Boolean): MyListGeneric[A] =
      if(predicate(h)) new Cons(h, t.filter(predicate))
      else t.filter(predicate)
    def ++[B >: A](list: MyListGeneric[B]): MyListGeneric[B] = new Cons(h, t ++ list)
    def flatMap[B](transformer: A => MyListGeneric[B]): MyListGeneric[B] =
      transformer(h) ++ t.flatMap(transformer)

  }

  // test run
  var listOfIntegers: MyListGeneric[Int] = new Cons(1, new Cons(2, new Cons(3, Empty)))
  var listOfStrings: MyListGeneric[String] = new Cons("A", new Cons("B", new Cons("C", Empty)))

  println(
    listOfIntegers
      .map( x => x * 2 )
      .toString
  )

  println(
    listOfIntegers
      .map( new Function[Int, Boolean]{
        override def apply(v1: Int): Boolean = v1 == 2
      } )
      .toString
  )

  println(
    listOfIntegers
      .filter( x => x == 2 )
      .toString
  )

}
