package lecture.part2OOP

object Exceptions extends App{

  val x:String = null
  // println(x.length) // will crash

  // throwable classes extend the Throwable class
  // Exception and Error are the major Throwable subtypes

  def getInt(withExceptions:Boolean): Int =
    if(withExceptions) throw new RuntimeException("No int for you")
    else 10

  val potentialFail: AnyVal =  try { // note that return type is AnyVal because of catch block
      getInt(true)
    } catch {
      case e: RuntimeException => println("Exception occured " + e.getMessage)
    } finally {
      // finally block doesn't influence the return type of this expression
      // use only side effect here
      println("finally happens all the time")
    }

  // How to define your own exceptions
  class MyException extends Exception
  val ex = new MyException




}
