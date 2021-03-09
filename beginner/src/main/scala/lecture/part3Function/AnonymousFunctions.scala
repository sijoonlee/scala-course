package lecture.part3Function

object AnonymousFunctions extends App{

  val doubler = new Function1[Int, Int] {
    override def apply(x:Int) = x * 2
  }

  val doublerAnon = (x: Int) => x * 2

  val doublerAnonShortHandNotation: Int => Int = x => x*2

  val adder = (a: Int, b:Int) => a + b

  val adder2: (Int, Int) => Int = (a, b) => a + b

  val noParam = () => 10

  println(noParam) // lecture.part3Function.AnonymousFunctions$$$Lambda$21/0x0000000840099c40@71be98f5
  println(noParam()) // 10

  // curly braces with lambdas
  val stringToInt = { (str:String) =>
    str.toInt
  }

  // MOAR syntactic sugar
  val niceIncrementer: Int => Int = _ + 1 // equivalent to x => x + 1
  val niceAdder: (Int, Int) => Int = _ + _ // equivalent to (a, b) => a + b

  val superAdd = (x:Int) => (y:Int) => x + y
}
