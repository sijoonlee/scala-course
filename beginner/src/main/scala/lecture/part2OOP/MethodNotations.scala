package lecture.part2OOP

object MethodNotations extends App{

  class Person(val name:String, favMovie: String){
    def likes(movie: String):Boolean = movie == favMovie
    def +(person:Person):String = s"${this.name} is hangout with ${person.name}"
    def unary_! = s"${this.name} !!!"
    def isAlive = true
    def apply(): String = s"Hey, I am ${this.name}"
  }

  val mary = new Person("Mary", "Star wars")

  println(mary.likes("Star wars"))
  println(mary likes "Star wars") // infix notation or operator notation

  // operators in scala
  val tom = new Person("Tom", "Fight Club")
  println(mary + tom)
  println(mary.+(tom))

  // All operators are methods
  // Akka actors have ! ?

  // prefix notation
  val x = -1 // unary operator
  val y = 1.unary_- // unary_ prefix only works with + -
  println(!mary)
  println(mary.unary_!)

  // postfix notation, only methods without parameters
  println(mary.isAlive)
  println(mary isAlive)

  // apply
  println(mary.apply())
  println(mary())


}
