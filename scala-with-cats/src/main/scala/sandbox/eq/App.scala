package eq

import feline.Cat

import cats._
import cats.implicits._

object App extends App {
  implicit val catEq: Eq[Cat] =
    Eq.instance[Cat] { (cat1, cat2) =>
      cat1.name == cat2.name &&
        cat1.age == cat2.age &&
          cat1.color == cat2.color
    }

  println(Cat("Sylvester", 13, "Black") =!= Cat("Garfield", 38, "Orange"))
  lazy val peppy = Cat("Peppy Le Pew", 31, "Black and White")
  println(peppy === peppy)
}
