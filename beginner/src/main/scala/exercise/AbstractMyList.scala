package exercise

abstract class AbstractMyList {
  def head: Int
  def tail: AbstractMyList
  def isEmpty: Boolean
  def add(element: Int): AbstractMyList
  def printElements: String
  override def toString: String = "[" + printElements + "]"
}

object Empty extends AbstractMyList {
  override def head: Int = throw new NoSuchElementException
  override def tail: AbstractMyList = throw new NoSuchElementException
  override def isEmpty: Boolean = true
  override def add(element: Int): AbstractMyList = new Cons(element, Empty)
  override def printElements: String = "Empty"
}

class Cons(h: Int, t: AbstractMyList) extends AbstractMyList {
  override def head: Int = h
  override def tail: AbstractMyList = t
  override def isEmpty: Boolean = false
  override def add(element: Int): AbstractMyList = new Cons(element, this)
  override def printElements: String =
    if(t.isEmpty) "" + h
    else h + " " + t.printElements
}

object ListTest extends App{
  val list1 = new Cons(1, Empty)
  println(list1.head)

  val list2 = new Cons(1, new Cons(2, Empty))
  println(list2.tail.head)
  println(list2.toString)
}