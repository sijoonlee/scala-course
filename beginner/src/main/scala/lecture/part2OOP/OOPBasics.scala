package lecture.part2OOP

object OOPBasics extends App{

  val person = new Person("AA", 26) // will print out 4
  println(person.x) // 2
  person.greet("JJ") // hello, JJ, this is AA

  val counter = new Counter(5)
  counter.inc.inc.print
  counter.inc.inc.inc.print

}

class Person(name: String, val age: Int = 0) { // constructor
  val x = 2

  println(1+3)

  def greet(name: String):Unit = println(s"hello, $name, this is ${this.name}")

  def this(name: String) = this(name, 0) // multiple constructor
}

class Counter(val count: Int){
  def inc = new Counter(count+1) // immutability
  def inc(n: Int): Counter = {
    if(n <= 0) this
    else inc.inc(n-1)
  }

  def dec = new Counter(count-1) // immutability
  def dec(n: Int): Counter = {
    if(n<=0) this
    else dec.dec(n-1)
  }

  def print = println(count)
}