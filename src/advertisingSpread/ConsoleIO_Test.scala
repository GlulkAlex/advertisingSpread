package advertisingSpread

object ConsoleIO_Test {
  /* if runs in interpreter: 
   * scala> ConsoleIO_Test.main(Array[String]())
   */
  def main(args: Array[String]) {
    print("Please enter your input : ")
    //val line = Console.readLine
    //scala.io.StdIn
    //Console.BLUE
    val line = scala.io.StdIn.readLine();//*works

    println("Thanks, you just typed: " + line);//*works
    //printif()
    println("Hi, " + scala.io.StdIn.readLine("You name is ?: ", 's') + "!");//*works
  }
}
