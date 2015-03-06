package advertisingSpread
//java.io.FileReader
import java.io._
import scala.io.Source

object fileIO_Test {
  def main( args: Array[ String ] ) {
    /* location in "project" folder:
     * "E:\Java\coursera-workspace\Challenge"
     */
    //val writer = new PrintWriter( new File( "fileIOtest.txt" ) ) //*works

    //writer.write( "Hello Scala!" ) //*works
//    writer.write( "Do you mind to work properly?" ) //*works
//    writer.close() //*works

    println( "Following is the content read:" )
    /*Source.scala -- This object provides 
    * convenience methods to 
    * create an iterable representation of a source file.
    */
    try {
      scala.io.Source
        .fromFile( "E:\\Java\\coursera-workspace\\Challenge\\fileIOtest.txt" )
        .foreach {
          print
        }
    } catch {
      case ex: Exception => println( ex ) //*works even better
    } //*works

    //default path needed
    //if not implicit
    val filename = "Test_1_input.txt"
    val filePath = "E:\\Java\\coursera-workspace\\Challenge\\"
    val currFile = Source
      .fromFile( filePath + filename )
    val currFileLines = Source
      .fromFile( filePath + filename )
      .getLines()
    /*val currFileBufferedReader = Source
      .fromFile( filePath + filename )
      .bufferedReader()*/
    val currFileBuffered = Source
      .fromFile( filePath + filename )
      .buffered;

    try {
      for (
        line <- Source
          .fromFile( filePath + filename )
          //*.LineIterator
          //*.isEmpty
          //*.length
          //*.reader()
          //*.iter
          //*.bufferedReader()
          //*.BufferedLineIterator
          .getLines()
      ) {
        println( "l>" + line )
      }
    } catch {
      //*case ex: Exception => println("Bummer, an exception happened.")//*works
      case ex: Exception => println( ex ) //*works even better
    } //*works

    //String line = bufferedReader.readLine();  
    /*scheme for any iterator*/
    //*while ( currFile.hasNext ) {
    println( "t>" + currFileLines take 1 )
    if ( currFileLines.hasNext ) {println( "1st>" + currFileLines.next() )}//*works
    while ( currFileLines.hasNext ) {
      /*reads file content char by char*/
      //*println( "w>" + currFile.next() )
      println( "w>" + currFileLines.next() )
    } //*work
    /*Note:
     * calling 'next' again on the same iterator will fail with a 'NoSuchElementException'.
     * */
    /* 'TraversableOnce' */
    /** In summary,
      * iterators behave like collections if
      * one never accesses an iterator again
      * after invoking a method on it.
      */
    /*must be equivalent to*/
    //*currFile foreach println //*work
    currFileLines/*.buffered*/ foreach ( elem => println( "f>" + elem ) ) //*work
    //currFile.reset()
    //currFile.close()
    /*or*/
    //*for ( elem <- currFile ) println( "e>" + elem ) //*work
    for ( elem <- currFile.iter ) print( "ei>" + elem ) //*work
    for ( elem <- currFileBuffered ) print( "eB>" + elem ) //*work
    /*There is only one standard operation which allows to 
     * re-use the same iterator: The call
     */
    //*val (it1, it2) = it.duplicate
    /*gives you 
     * two iterators which 
     * each return 
     * exactly the same elements as the iterator 'it'. 
     * The two iterators work independently; 
     * advancing one does not affect the other. 
     * By contrast 
     * the original iterator 'it' is 
     * advanced to its end by 'duplicate' and 
     * is thus rendered unusable.  
     */

  }

}