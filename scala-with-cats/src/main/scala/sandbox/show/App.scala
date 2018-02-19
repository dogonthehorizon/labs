package show

import cats._
import cats.implicits._

final case class Cat(name: String, age: Int, color: String)

object App extends App {

  // meow.
  implicit val catShow: Show[Cat] =
    Show.show(cat => {
      lazy val name  = cat.name.show
      lazy val age   = cat.age.show
      lazy val color = cat.color.show

      s"$name is a $age year old $color cat."
    })

  println(Cat("Sylvester", 999, "Black").show)
}
