package printable

final case class Cat(name: String, age: Int, color: String)

object App extends App {
  import PrintableInstances._
  import PrintableSyntax._

  implicit val catPrinter: Printable[Cat] =
    new Printable[Cat] {
      def format(cat: Cat): String = {
        val name = Printable.format(cat.name.capitalize)
        val age = Printable.format(cat.age)
        val color = Printable.format(cat.color.toLowerCase)

        s"$name is a $age year old $color"
      }
    }

  Cat("Sylvester", 999, "Black").print
}
