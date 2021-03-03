package lecture.part3Function

object WhatIsFunction extends App {

  // Dream - use functions as first class elements
  // Problem - OOP

  val doubler = new MyFunc[Int, Int] {
    override def apply(element: Int):Int = element * 2;
  }

  println(doubler(2))

  // function types = Function1[A, B]
  val stringToIntConverter = new Function1[String, Int] {
    override def apply(str:String): Int = str.toInt
  }

  println(stringToIntConverter("3") + 5)

  // All Scala functions are objects


  // Higher Order Function - Function1[Int, Function1[Int, Int]]
  val higher:Function1[Int, Function1[Int, Int]] = new Function1[Int, Function1[Int, Int]]{
    override def apply(x: Int): Function1[Int, Int] = new Function1[Int, Int]{
      override def apply(y: Int): Int = x + y
    }
  }

  val returned = higher(10)
  println(returned(20))
  println(higher(10)(20)) // this is called curried function

}

trait MyFunc[A, B]{
  def apply(element: A): B
}