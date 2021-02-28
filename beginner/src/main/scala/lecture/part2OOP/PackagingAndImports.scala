package lecture.part2OOP

object PackagingAndImports extends App{

  // Package members are accessible by their name
  val writer = new Person("Dan")

  // if a class is outside of the current package,
  // 1) you need to import
  //    import abc.def
  //    val author = new Author()
  // 2) or use fully qualified name
  //    val author = new abc.def.Author()

  // package object
  sayHello

  // import class
  // All ex) import playground._
  // Prince, Princess ex) import playground.{Prince, Princess}

  // import class with alias
  // ex) import java.sql.(Date => SqlDate)
  //     you can use SqlDate now

  // default imports
  // java.lang - String, Object, Exception
  // scala - int, Nothing, Function
  // scala.Predef - println, ???
}
