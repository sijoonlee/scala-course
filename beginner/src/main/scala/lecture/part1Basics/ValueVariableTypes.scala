package lecture.part1Basics

object ValueVariableTypes extends App{

  /**
   * val
   */

  val x: Int = 1
  println(x)

  /* you can't do this since val is immutable
  x = 2
  */

  val y = 1 // compiler can infer types

  val stringVal: String = "this is string"

  val booleanVal: Boolean = true // or false

  val charVal: Char = 'a' // single quote

  val shortVal: Short = 11

  val longVal: Long = 111111111111111111L // note 'L'

  val floatVal: Float = 2.0f

  val doubleVal: Double = 2.0


  /**
   * var
   */

  var a: Int = 1

  a = 3 // side effect



}
