package lecture.part2OOP

object PracticeList extends App{

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

    def map[B](transformer: MyTransformer[A, B]): MyListGeneric[B]
    def filter(predicate: MyPredicate[A]): MyListGeneric[A]
    def ++[B >: A](list: MyListGeneric[B]): MyListGeneric[B]
    def flatMap[B](transformer: MyTransformer[A, MyListGeneric[B]]): MyListGeneric[B]
  }

  object Empty extends MyListGeneric[Nothing]{
    override def head: Nothing = throw new NoSuchElementException
    override def tail: MyListGeneric[Nothing] = throw new NoSuchElementException
    override def isEmpty: Boolean = true
    override def add[B >: Nothing](element: B): MyListGeneric[B] = new Cons(element, Empty)
    override def printElements: String = ""
    def map[B](transformer: MyTransformer[Nothing, B]) : MyListGeneric[B] = Empty
    def filter(predicate: MyPredicate[Nothing]): MyListGeneric[Nothing] = Empty
    def ++[B >: Nothing](list: MyListGeneric[B]): MyListGeneric[B] = list
    def flatMap[B](transformer: MyTransformer[Nothing, MyListGeneric[B]]): MyListGeneric[B] = Empty

  }

  class Cons[+A](h:A, t:MyListGeneric[A]) extends MyListGeneric[A] {
    override def head: A = h
    override def tail: MyListGeneric[A] = t
    override def isEmpty: Boolean = false
    override def add[B >: A](element: B): MyListGeneric[B] = new Cons(element, this)
    override def printElements: String =
      if(t.isEmpty) "" + h
      else h + " " + t.printElements
    def map[B](transformer: MyTransformer[A, B]) : MyListGeneric[B] =
      new Cons(transformer.transform(h), t.map(transformer))
    def filter(predicate: MyPredicate[A]): MyListGeneric[A] =
      if(predicate.test(h)) new Cons(h, t.filter(predicate))
      else t.filter(predicate)
    def ++[B >: A](list: MyListGeneric[B]): MyListGeneric[B] = new Cons(h, t ++ list)
    def flatMap[B](transformer: MyTransformer[A, MyListGeneric[B]]): MyListGeneric[B] =
      transformer.transform(h) ++ t.flatMap(transformer)

  }

  trait MyPredicate[-T]{
    def test(elem: T):Boolean
  }

  trait MyTransformer[-A, B] {
    def transform(elem:A):B
  }



  // test run
  var listOfIntegers: MyListGeneric[Int] = new Cons(1, new Cons(2, new Cons(3, Empty)))
  var listOfStrings: MyListGeneric[String] = new Cons("A", new Cons("B", new Cons("C", Empty)))

  println(
    listOfIntegers
      .map(new MyTransformer[Int, Int] { override def transform(elem: Int): Int = elem * 2 })
      .toString
  )
  println(
    listOfIntegers
      .filter(new MyPredicate[Int] { override def test(elem: Int): Boolean = elem == 2 })
      .toString
  )

}
