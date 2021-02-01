package lecture.part1Basics

object DefaultArg extends App{
  def trFact(n:Int, acc:Int = 1): Int =
    if(n<=1) acc
    else trFact(n-1, n*acc)

  //val fact10 = trFact(10,1)
  val fact10 = trFact(10) // acc has default value of 1

  def example(format:String = "jpg", width:Int = 100, height:Int = 100): Unit = println("---")

  example(width = 200)

}
