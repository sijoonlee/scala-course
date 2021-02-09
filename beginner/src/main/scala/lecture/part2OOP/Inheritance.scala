package lecture.part2OOP

object Inheritance extends App {

  // Single Class Inheritance
  class Animal {
    val creatureType = "wild"
    def eat = println("nomnom")
  }

  class Cat extends Animal {
    def crunch = {
      eat // protected attr can be accessed inside of sub-class
      println("crunch")
    }
  }

  val cat = new Cat
  // cat.eat // but protected attr in parent can't be used from outside
  cat.crunch

  // constructors
  class Person(name:String, age:Int){
    def this(name:String) = this(name, 0)
  }
  class Adult(name:String, age:Int, idCard:String) extends Person(name, age) // Person(name, age) or Person(name)

  // Overriding
  class Dog(override val creatureType: String = "domestic") extends Animal {
    override def eat: Unit = {
      super.eat // Animal's eat
      println("WoofNomWoofNom")
    }
  }
  val dog = new Dog
  dog.eat
  println(dog.creatureType)

  // Type Substitution
  val unknownAnimal: Animal = new Dog("K9")
  unknownAnimal.eat // WoofNomWoofNom

  // Note that Overloading =/= Overriding

  // super

  // how to prevent overrides ?
  // 1 - use final keyword on member
  // 2 - use final keyword on class itself
  // 3 - use seal keyword on class = extend classes in this file / but not in other files


}
