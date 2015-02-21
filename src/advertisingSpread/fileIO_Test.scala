package advertisingSpread
//java.io.FileReader
import java.io._
import scala.io.Source

object fileIO_Test {
  def main(args: Array[String]) {
    /* location in "project" folder:
     * "E:\Java\coursera-workspace\Challenge"
     */
    val writer = new PrintWriter(new File("fileIOtest.txt")) //*works

    writer.write("Hello Scala!") //*works
    writer.write("Do you mind to work properly?") //*works
    writer.close() //*works

    println("Following is the content read:")
    /*Source.scala -- This object provides 
    * convenience methods to 
    * create an iterable representation of a source file.
    */
    try {
      scala.io.Source
        .fromFile("E:\\Java\\coursera-workspace\\Challenge\\fileIOtest.txt")
        .foreach {
          print
        }
    } catch {
      case ex: Exception => println(ex) //*works even better
    } //*works

    //default path needed
    //if not implicit
    val filename = "fileIOtest.txt"
    val filePath = "E:\\Java\\coursera-workspace\\Challenge\\"

    try {
      for (
        line <- Source
          .fromFile(filePath + filename)
          .getLines()
      ) {
        println(line)
      }
    } catch {
      //*case ex: Exception => println("Bummer, an exception happened.")//*works
      case ex: Exception => println(ex) //*works even better
    } //*works
  }
}