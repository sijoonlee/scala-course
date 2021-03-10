package lecture.part3Function

object HOFsExercise extends App{

  /*
   1.  Expand MyList
       - foreach method A => Unit
         [1,2,3].foreach(x => println(x))
       - sort function ((A, A) => Int) => MyList
         [1,2,3].sort((x, y) => y - x) => [3,2,1]
       - zipWith (list, (A, A) => B) => MyList[B]
         [1,2,3].zipWith([4,5,6], x * y) => [1 * 4, 2 * 5, 3 * 6] = [4,10,18]
       - fold(start)(function) => a value
         [1,2,3].fold(0)(x + y) = 6
   2.  toCurry(f: (Int, Int) => Int) => (Int => Int => Int)
       fromCurry(f: (Int => Int => Int)) => (Int, Int) => Int
   3.  compose(f,g) => x => f(g(x))
       andThen(f,g) => x => g(f(x))
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

    def foreach(f: A => Unit): Unit
    def sort(compare: (A,A) => Int): MyListGeneric[A]
    def zipWith[B,C](list:MyListGeneric[B], zip:(A, B) => C): MyListGeneric[C]
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

    // hofs
    def foreach(f: Nothing => Unit): Unit = ()
    def sort(compare:(Nothing, Nothing)=>Int) = Empty
    def zipWith[B, C](list:MyListGeneric[B], zip:(Nothing, B) => C): MyListGeneric[C] =
      if(!list.isEmpty) throw new RuntimeException("Lists do not have the same length")
      else Empty
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

    // hofs
    def foreach(f:A => Unit): Unit = {
      f(h)
      t.foreach(f)
    }

    def sort(compare:(A, A) => Int): MyListGeneric[A] = {
      def insert(x: A, sortedList: MyListGeneric[A]): MyListGeneric[A] = {
        if(sortedList.isEmpty) new Cons(x, Empty)
        else if(compare(x, sortedList.head)<= 0) new Cons(x, sortedList)
        else new Cons(sortedList.head, insert(x, sortedList.tail))
      }
      val sortedTail = t.sort(compare)
      insert(h, sortedTail)
    }

    def zipWith[B, C](list: MyListGeneric[B], zip: (A, B) => C): MyListGeneric[C] =
      if(list.isEmpty) throw new RuntimeException("Lists do not have the same length")
      else new Cons(zip(h, list.head), t.zipWith(list.tail, zip))
  }

  var listOfIntegers: MyListGeneric[Int] = new Cons(1, new Cons(2, new Cons(3, Empty)))
  var listOfIntegers2: MyListGeneric[Int] = new Cons(2, new Cons(3, new Cons(4, Empty)))
  listOfIntegers.foreach(println)
  println(listOfIntegers.sort( (x,y) => y-x ))
  println(listOfIntegers.zipWith[Int, Int](listOfIntegers2, _ + _))

  def toCurry(f: (Int, Int) => Int): (Int => Int => Int) =
    x => y => f(x, y)

  def fromCurry(f: (Int => Int => Int)): (Int, Int) => Int =
    (x, y) => f(x)(y)

  // FunctionX
  def compose[A,B,T](f: A => B, g: T => A): T => B =
    x => f(g(x))

  def andThen[A,B,C](f: A => B, g: B => C): A => C =
    x => g(f(x))

  def superAdder2: (Int => Int => Int) = toCurry(_ + _)
  def add4 = superAdder2(4)
  println(add4(17))

  val superAdder: Int => (Int => Int) = (x: Int) => (y: Int) => x + y
  val simpleAdder = fromCurry(superAdder)
  println(simpleAdder(4,17))

  val add2 = (x: Int) => x + 2
  val times3 = (x: Int) => x * 3

  val composed = compose(add2, times3)
  val ordered = andThen(add2, times3)

  println(composed(4))
  println(ordered(4))

}
