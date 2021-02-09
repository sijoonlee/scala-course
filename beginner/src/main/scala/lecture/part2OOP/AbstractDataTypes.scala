package lecture.part2OOP

object AbstractDataTypes extends App {

  // abstract
  abstract class Animal {
    val creatureType: String
    def eat: Unit

  }

  class Dog extends Animal {
    override val creatureType: String = "Canine"
    override def eat: Unit = println("Woof nom nom")
  }

  trait ColdBlooded

  trait Carnivore {
    def eat(animal: Animal): Unit

  }

  class Crocodile extends Animal with Carnivore with ColdBlooded {
    override val creatureType: String = "croc"

    override def eat: Unit = "chop chop"

    override def eat(animal: Animal): Unit = println(s"chop chop ${animal.creatureType}")

  }

  val dog = new Dog
  val croc = new Crocodile
  croc.eat(dog)

  // trait vs abstract class
  // Note that both trait and abstract class can have abstract and non-abstract member
  // 1 - traits : don't have constructor param
  // 2 - multiple traits may be inherited
  // 3 - traits ~ behaviors vs abstract class ~ things


}
