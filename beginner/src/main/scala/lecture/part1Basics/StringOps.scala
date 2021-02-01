package lecture.part1Basics

object StringOps extends App{
  val str:String = "hello"

  println(str.charAt(2)) // l
  println(str.substring(0,2)) // he
  println(str.split('l').toList) // List(he, , o)
  println(str.startsWith("hell")) // true
  println(str.replace("l", "L")) // heLLo
  println(str.toLowerCase())
  println(str.toUpperCase())
  println(str.length)
  val aNumbStr = "2"
  val aNumb = aNumbStr.toInt
  println('a' +: aNumbStr :+ 'z') // a2z
  println(str.reverse)
  println(str.take(2))

  /**
   * Scala specific String interpolators
   * https://docs.scala-lang.org/overviews/core/string-interpolation.html
   */
    
  val name = "David"
  val age = 12
  val greeting = s"Hello, $name, ${age + 1}"
  println(greeting) // Hello, David, 13

  /**
   * F-interpolator
   */
  val speed = 1.2f
  val myth = f"$name%s $speed%2.2f" // %s = string
  println(myth) // David 1.20

  /**
   * raw-interpolator
   */
  println(raw"This is a \n newline")
  // This is a \n newline
  // The raw interpolator is useful when you want to avoid having expressions like \n turn into a return character.

  // be careful
  val escaped = "This is a \n newline"
  println(raw"$escaped")
  //This is a
  // newline

}
