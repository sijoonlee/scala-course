package lecture.part1Basics

object Expressions extends App{
  val x = 1 + 2 // expression
  println(x)

  println(2 + 3 * 4)

  println(1 == x)

  var variable = 2

  variable += 3 // side effect

  println(variable)

  /**
   * Instructions vs Expressions
   * Instructions = something executed
   * Expressions = something evaluated
   */

  // if expr
  val aCondition = true
  val aConditionedValue = if(aCondition) 5 else 3 // if expression
  println(if(aCondition) 5 else 3)

  // instruction -> don't do this in Scala
  // side effect returns unit
  // aWhile: Unit
  var i = 0
  val aWhile = while(i<10){
    println(i)
    i += 1
  }

  // Use expression in Scala instead of instruction
  val strangeVal = (variable = 3) // unit === void

  println(strangeVal) // only shows ()

  // side effects : println(), whiles, reassigning

  // code blocks are expressions
  val codeBlock:String = {
    val y = 2
    val z = y + 1
    if(z>2) "hello" else "goodbye"
  }



}
