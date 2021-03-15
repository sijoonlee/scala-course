package lecture.part3Function

object MapFlatMapFilterFor extends App {

  val list = List(1,2,3)

  println(list.head)
  //1
  println(list.tail)
  // List(2, 3)

  println(list.map(_+1))
  //List(2, 3, 4)
  println(list.map(_+" is a number"))
  //List(1 is a number, 2 is a number, 3 is a number)

  println(list.filter(_%2 == 0))
  //List(2)

  val toPair = (x:Int) => List(x, x+1)

  println(list.flatMap(toPair))
  //List(1, 2, 2, 3, 3, 4)

  val numbers = List(1,2,3,4)
  val chars = List('a','b','c','d')
  val colors = List("black", "white")
  val combinations = numbers.flatMap(n => chars.map(c => "" + c + n))
  println(combinations)
  //List(a1, b1, c1, d1, a2, b2, c2, d2, a3, b3, c3, d3, a4, b4, c4, d4)

  val combinations2 = numbers
    .filter(_ % 2 == 0)
    .flatMap( n => chars.flatMap( c => colors.map(color => "" + c + n + "-" + color)))
  println(combinations2)

  list.foreach(println)

  // for-comprehensions
  val forCombinations = for {
    n <- numbers if n % 2 == 0
    c <- chars
    color <- colors
  } yield "" + c + n + "-" + color
  println(forCombinations)


}
