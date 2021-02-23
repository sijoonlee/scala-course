package lecture.part2OOP

object AnonymousClasses extends App {

  trait Animal { // you can use abstract class
    def eat: Unit
  }

  // Anonymous Class
  val funnyAnimal: Animal = new Animal {
    override def eat: Unit = println("hahahah")
  }

  println(funnyAnimal.getClass)
  // class lecture.part2OOP.AnonymousClasses$$anon$1
  // compiler creates class that extends Animal under the hood

  class Person(name: String){
    def sayHi:Unit = println(s"Hi, My name is $name")
  }

  val jim = new Person("Jim") {
    override def sayHi: Unit = println(s"Hello, I am $this.name")
  }

}
