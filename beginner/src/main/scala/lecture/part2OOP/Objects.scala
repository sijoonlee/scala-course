package lecture.part2OOP

object Objects extends App{

  // Scala does not know class-level functionality (ex class level static variable)
  object Person { // this defines type + its only instance
    // static-like functionality
    val N_EYES = 2
    def canFly: Boolean = false

    // factory method
    def from(mother: Person, father: Person): Person = new Person("Bobbie")
  }

  class Person(val name:String) {
    // instance-level functionality
  }
  // object Person and class Person have same name in same scope
  // --> Companions: they can access each other's private members


  println(Person.N_EYES)
  println(Person.canFly)

  // Scala Object = Singleton instance
  val p1 = Person
  val p2 = Person
  println(p1 == p2) // true since it's from Singleton Person

  val mary = new Person("mary") // this is an instance from Class Person
  val john = new Person("john") // this is an instance from Class Person
  println(mary == john) // false

  val bobbie = Person.from(mary, john)

  // Scala application = Scala object with
  // def main(args: Array[String]): Unit

}
