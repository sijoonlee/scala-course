package lecture.part3Function

import scala.util.Random

object SequenceListVector extends App {

  // SEQ
  val aSequence = Seq(1,2,3,4)

  println(aSequence)
  println(aSequence.reverse)
  println(aSequence(2)) // 3
  println(aSequence ++ Seq(5,5,5))
  println(aSequence.sorted)

  val aRange: Seq[Int] = 1 to 10
  aRange.foreach(println)

  (1 to 10).foreach(println)

  // LIST
  val aList = List(1,2,3)
  val prepended = 42 :: aList
  println(prepended) // List(42, 1, 2, 3)

  val prepended2 = 42 +: aList :+ 89
  println(prepended2) // List(42, 1, 2, 3, 89)

  val apple5 = List.fill(5)("apple")
  println(apple5) // List(apple, apple, apple, apple, apple)
  println(apple5.mkString(",")) // apple,apple,apple,apple,apple

  // ARRAY (mutable)
  val numbers = Array(1,2,3,4)
  val treeElements = Array.ofDim[String](3)
  treeElements.foreach(println)
//  null
//  null
//  null
  numbers(2) = 0 // equivalent to numbers.update(2,0)
  println(numbers.mkString(",")) // 1,2,0,4

  val numberSeq: Seq[Int] = numbers // implicit conversion
  println(numberSeq) // ArraySeq(1, 2, 0, 4)


  // Vector (immutable)
  val vector: Vector[Int] = Vector(1,2,3)


  // vectors vs lists
  val maxRuns = 1000
  val maxCapacity = 100000
  def getWriteTime(collection: Seq[Int]): Double = {
    val r = new Random
    val times = for {
      it <- 1 to maxRuns
    } yield {
      val currentTime = System.nanoTime()
      collection.updated(r.nextInt(maxCapacity), r.nextInt())
      System.nanoTime() - currentTime
    }
    times.sum * 1.0 / maxRuns
  }

  val numbersList = (1 to maxCapacity).toList
  val numbersVector = (1 to maxCapacity).toVector

  // keeps reference to tail
  // updating an element in the middle takes long
  println(getWriteTime(numbersList)) //782800.648

  // dept of the tree is small
  // needs to replace an entire 32-element chunk
  println(getWriteTime(numbersVector)) //7358.906







}
