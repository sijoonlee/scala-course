package lecture.part1Basics

object Functions extends App{
  def funcA(a:String, b:Int):String =
    a + " " + b
  println(funcA("Test", 1))

  def funcB(): Int = 42
  println(funcB) // 42
  println(funcB()) // 42

  // recursive func instead of loop
  def repeated(a:String, n:Int): String = {
    if(n==1) a
    else a + repeated(a, n-1)
  }
  println(repeated("Test", 3)) // TestTestTest

  def funcWithSideEffect(a:String):Unit =
    println(a)

  // Factorial (n) => 1 * 2 * 3 ... * n
  def factorial(a: Int):Int = {
    if(a == 1) a
    else a * factorial(a-1)
  }
  println(factorial(3)) // 6


  // Fibonacci f(1) = 1, f(2) = 2, f(n) = f(n-1) + f(n-2)
  def fibonacci(a: Int):Int = {
    if(a==1) a
    else if(a==2) a
    else fibonacci(a-1) + fibonacci(a-2)
  }
  println(fibonacci(5)) // f(4) + f(3) = f(3) + f(2) + f(2) + f(1) = f(2) + f(1) + f(2) + f(2) + f(1) = 5

  // is Prime?
  def isPrime(a:Int): Boolean = {
    def isPrimeUntil(t:Int):Boolean = {
      if(t<=1) true
      else a % t != 0 && isPrimeUntil(t-1)
    }
    isPrimeUntil(a/2)
  }
}
