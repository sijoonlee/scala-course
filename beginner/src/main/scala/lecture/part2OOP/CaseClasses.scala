package lecture.part2OOP

object CaseClasses extends App{

  case class Person(name: String, age: Int)

  // 1. class parameters are fields
  val jim = new Person("Jim", 34)
  println(jim.name)

  // 2. sensible toString
  println(jim.toString)

  // 3. equals and hashCode implemented out of the box
  val jim2 = new Person("Jim", 34)
  println(jim == jim2) // true

  // 4. case classes have handy copy method
  val jim3 = jim.copy(age = 45)
  println(jim3)

  // 5. case classes have companion objects
  val thePerson = Person
  val mary = Person("Mary", 50)

  // 6. case classes are serializable
  // Akka

  // 7. case classes have extractor patterns => CCs can be used in Pattern Matching

  // case object
  case object UnitedKingdom{
    def name: String = "UK of GB adn NI"

  }

  /**
   * Expand MyList - use CC and CO
   * --> see PracticeList
   */



}
