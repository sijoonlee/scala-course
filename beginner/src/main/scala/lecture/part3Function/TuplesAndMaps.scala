package lecture.part3Function

import scala.annotation.tailrec

object TuplesAndMaps extends App{

  // tuple = finite ordered "lists"
  val aTuple = new Tuple2(2, "hello, scala") // Tuple2[Int, String] = (Int, String)
  val bTuple = (2,"hello, scala")

  println(aTuple._1) // 2
  println(aTuple.copy(_2 = "goodbye")) // (2,goodbye)
  println(aTuple.swap) // (hello, scala,2)

  // Maps = key/value pair
  val aMap: Map[String, Int] = Map()
  val phoneBook = Map(("Jim", 111), "Jone" -> 222) // a -> b is syntax sugar for (a, b)
  println(phoneBook)

  println(phoneBook.contains("Jim")) // true
  println(phoneBook("Jim")) // 111

  val phoneBookWithDefault = Map(("Jim", 111), "Jone" -> 222).withDefaultValue(-1)
  println(phoneBookWithDefault("Nobody")) // -1

  val newPhoneBook = phoneBook + "Mary" -> 678
  println(newPhoneBook)

  println(phoneBook.map(pair => pair._1.toLowerCase() -> pair._2))
  /* What would happen if I had two original entries "Jim" -> 555 and "Jim" -> 900 ?
      You will lose information in doing : phoneBook.map(pair => pair._1.toLowerCase() -> pair._2)
   */

  println(phoneBook.view.filterKeys(x => x.startsWith("J")).toMap)

  println(phoneBook.view.mapValues(number => "100" + number).toMap) // Map(Jim -> 100111, Jone -> 100222

  println(phoneBook.toList) // List((Jim,111), (Jone,222))

  val names = List("bob", "james", "bing")
  println(names.groupBy(name => name.charAt(0))) // HashMap(j -> List(james), b -> List(bob, bing))


  /**
   * Overly simplified social network based on maps
   */

  def add(network: Map[String, Set[String]], person:String): Map[String, Set[String]] =
    network + (person -> List())

  def friend(network: Map[String, Set[String]], a: String, b: String): Map[String, Set[String]] = {
    val friendsA = network(a)
    val friendsB = network(b)

    network + (a -> (friendsA + b)) + (b -> (friendsB + a))
  }

  def unfriend(network: Map[String, Set[String]], a: String, b: String): Map[String, Set[String]] = {
    val friendsA = network(a)
    val friendsB = network(b)

    network + (a -> (friendsA - b)) + (b -> (friendsB - a))
  }

  def remove(network: Map[String, Set[String]], person: String): Map[String, Set[String]] = {
    def removeAux(friends: Set[String], networkAcc: Map[String, Set[String]]): Map[String, Set[String]] =
      if (friends.isEmpty) networkAcc
      else removeAux(friends.tail, unfriend(networkAcc, person, friends.head))

    val unfriended = removeAux(network(person), network)
    unfriended - person
  }

  val empty: Map[String, Set[String]] = Map()
  val network = add(add(empty, "Bob"), "Mary")
  println(network)
  println(friend(network, "Bob", "Mary"))
  println(unfriend(friend(network, "Bob", "Mary"), "Bob", "Mary"))
  println(remove(friend(network, "Bob", "Mary"), "Bob"))

  // Jim,Bob,Mary
  val people = add(add(add(empty, "Bob"), "Mary"), "Jim")
  val jimBob = friend(people, "Bob", "Jim")
  val testNet = friend(jimBob, "Bob", "Mary")

  println(testNet)

  def nFriends(network: Map[String, Set[String]], person: String): Int =
    if (!network.contains(person)) 0
    else network(person).size

  println(nFriends(testNet, "Bob"))

  def mostFriends(network: Map[String, Set[String]]): String =
    network.maxBy(pair => pair._2.size)._1

  println(mostFriends(testNet))

  def nPeopleWithNoFriends(network: Map[String, Set[String]]): Int =
    network.count(_._2.isEmpty)

  println(nPeopleWithNoFriends(testNet))

  def socialConnection(network: Map[String, Set[String]], a: String, b: String): Boolean = {
    @tailrec
    def bfs(target: String, consideredPeople: Set[String], discoveredPeople: Set[String]): Boolean = {
      if (discoveredPeople.isEmpty) false
      else {
        val person = discoveredPeople.head
        if (person == target) true
        else if (consideredPeople.contains(person)) bfs(target, consideredPeople, discoveredPeople.tail)
        else bfs(target, consideredPeople + person, discoveredPeople.tail ++ network(person))
      }
    }

    bfs(b, Set(), network(a) + a)
  }

  println(socialConnection(testNet, "Mary", "Jim"))
  println(socialConnection(network, "Mary", "Bob"))



}
