

object Main {
  class Container[A](private val wrt: A ){
    def getContent(): A = wrt
    def applyFunction[R](f: A => R): R = f(wrt)

  }

  trait Maybe[A]{
    def getOrElse[B >: A](param:B) : B
    def applyFunction[R](f:A => R): Maybe[_]
  }

  class No extends Maybe[Nothing]{
    override def applyFunction[R](f:Nothing => R):No= { new No() }
    override def getOrElse[B](param:B):B = param
  }

  class Yes[A](var x: A) extends Maybe[A]{
    override def applyFunction[R](f: A => R): Yes[R] = {new Yes(f(x))}
    override def getOrElse[B >: A](param:B):B = x

  }

  def main(args: Array[String]){

    println("Zadanie 1")
    val container = new Container("Nazywam się ")

    println("Zawartość container: " + container.getContent())

    def sub(s:String):String={
      s + "Admirał Gwiezdnej Floty"
    }

    println("applyFunction na container", container.applyFunction(sub))

    println("Zadanie 2")

    val ob1 = new No()
    val ob2 = new Yes("Mkbewe uno duo")

    println("Is ob1 instance of Maybe: " + ob1.isInstanceOf[Maybe[_]])
    println("Is ob2 instance of Maybe: " + ob2.isInstanceOf[Maybe[_]])

    println("Zadanie 3")

    val ob11 = ob1.applyFunction(sub)
    val ob22 = ob2.applyFunction(sub)

    println(ob11.isInstanceOf[No])
    println(ob22.getOrElse("XD"))

    println("Zadanie 4")
    println("getOrElse dla No: " + ob1.getOrElse(2137))
    println("getOrElse dla Yes: " + ob2.getOrElse(21.37))

  }
}
