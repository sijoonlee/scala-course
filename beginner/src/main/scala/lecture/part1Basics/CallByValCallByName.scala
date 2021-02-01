package lecture.part1Basics

object CallByValCallByName extends App{

  def calledByValue(x:Long):Unit = {
    println("by val: " + x)
    println("by val: " + x)
  }

  def calledByName(x: => Long):Unit = {
    println("by name: " + x) // expression, not the value itself
    println("by name: " + x)
  }

  calledByValue(System.nanoTime())
//  by val: 52535186427704
//  by val: 52535186427704 // same

  calledByName(System.nanoTime())
//  by name: 52535258665190
//  by name: 52535260781387 // different

  def infinite():Int = 1 + infinite() // will cause stack overflow

  def printFirst(x:Int, y: =>Int) = println(x)

  // printFirst(infinite(), 10) // error since evaluation comes first
  printFirst(10, infinite()) // no error because of lazy evaluation


}
