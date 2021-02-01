package lecture.part1Basics

import scala.annotation.tailrec

object Recursion extends App {

  def factorial(n: Int): Int = {
    if(n<=1) 1
    else {
      println("START: " + n )
      val result = n * factorial(n-1)
      println("FINISH: " + n )
      result
    }
  }

  factorial(5)
//  START: 5
//  START: 4
//  START: 3
//  START: 2
//  FINISH: 2
//  FINISH: 3
//  FINISH: 4
//  FINISH: 5
// Note that there is stack - recursive depth - which will take up memory space


  def factorialTail(n: Int): Int = {
    @tailrec // scala preserves the same stack frame and does not use additional stack frame for recursive calls
    def helper(x:Int, acc:Int): Int = {
      if(x<=1) acc
      else helper(x - 1, x * acc)
    }
    helper(n, 1)
  }

  @tailrec
  def concatenateTailRec(a:String, n:Int, acc:String):String =
    if (n<=0) acc
    else concatenateTailRec(a, n-1, a+acc)


  def isPrime(n:Int):Boolean = {
    @tailrec
    def helper(t:Int, isStillPrime:Boolean):Boolean = {
      if(!isStillPrime) false
      else if(t<=1) true
      else helper(t-1, n%t!=0 && isStillPrime)
    }
    helper(n/2, true)
  }

  def fibonacci(n:Int): Int = {
    @tailrec
    def helper(i:Int, last:Int, nextToLast:Int): Int ={
      if(i>=n) last
      else helper(i+1, last + nextToLast, last)
    }
    if(n<=2) 1
    else helper(2,1,1)
  }
}
