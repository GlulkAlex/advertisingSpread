package test

import org.scalatest._
/*Style Trait
 * 'FlatSpec' */
abstract class UnitSpec extends FlatSpec with Matchers with OptionValues with Inside with Inspectors
/*To include 'ScalaTest' in your 'sbt' project, 
 * simply add this line (to 'build.sbt'):
 * !!! Note: changing content may cause unpredictable crashes due to 
 * unresolved dependencies !!!
  libraryDependencies += "org.scalatest" % "scalatest_2.11" % "2.2.1" % "test"
  * */
class SpanTreeSpec extends UnitSpec {
  /*The central concept in 'ScalaTest' is the 'suite', 
   * a collection of zero to many 'tests'.
   *A test can be 
   * anything with a 'name' that can 
   * 'start' and 
   * either 
   * 'succeed', 'fail', 'be pending', or 'canceled'.
   */
  // Your tests here
  /*package.object / file */ //greedyAlgorithms.schedulesJobsDifference._
  import advertisingSpread.Solution._

  /*println( "expected # for Test_1_output.txt is: " + 2 )
    println( "expected # for Test_2_output.txt is: " + 2 )
    println( "expected # for Test_3_output.txt is: " + 3 )
    println( "expected # for Test_4_output.txt is: " + 5 )
    println( "expected # for Test_5_output.txt is: " + 5 )
    println( "expected # for Test_6_output.txt is: " + 7 )
    println( "expected # for Test_7_output.txt is: " + 15 )
    println( "expected # for Test_8_output.txt is: " + 9 )
    println( "expected # for Test_9_output.txt is: " + 15 )
    println( "expected # for Test_10_output.txt is: " + 5 )*/
  "Expected test_1 result" should "return #2 for Test_1_output.txt " in {
    assume( inputTest( 1 ) == 2 )
    /*assertResult( 2 ) {
      inputTest( 1 )
    }*/
  }
  "Expected test_2 result" should "return #2 for Test_2_output.txt " in {
	  //*assume( inputTest( 1 ) == 2 )
	  assertResult( 2 ) {
      inputTest( 2 )
    }
  }
  "Expected test_3 result" should "return #3" in {
	  assertResult( 3 ) {
		  inputTest( 3 )
	  }
  }
  "Expected test_4 result" should "return #5" in {
	  assertResult( 5 ) {
		  inputTest( 4 )
	  }
  }
  "Expected test_5 result" should "return #5" in {
	  assertResult( 5 ) {
		  inputTest( 5 )
	  }
  }
  /*'key not found: 1036'
   *? unsafe element 'get' from 'Map' ?*/
  "Expected test_6 (cheaty input) result" should "return #7" in {
	  assertResult( 7 ) {
		  inputTest( 6 )
	  }
  }
  /*Expected 15, but got 8
   * ? must be 'root'.rank ?*/
  "Expected test_7 result" should "return #15" in {
	  assertResult( 15 ) {
		  inputTest( 7 )
	  }
  }
  "Expected test_8 result" should "return #9" in {
	  assertResult( 9 ) {
		  inputTest( 8 )
	  }
  }
  /*Run completed in 11 minutes, 51 seconds.*/
  "Expected test_9 result" should "return #15" in {
	  assertResult( 15 ) {
		  inputTest( 9 )
	  }
  }
  "Expected test_10 result" should "return #5" in {
	  assertResult( 5 ) {
		  inputTest( 10 )
	  }
  }
  /*if
   *class 'extends FunSuite'*/
  /*test( "'mockUpMST' size\n " +
    "for 'mockUpInputGraph'\n" +
    "should have bin ? " + 7 + " ?" ) {
    /*too large for integer*/
    assume( mockUpMST.length == 7 )
  }*/
  
  /*Tagging tests as 'ignored'
  To support the common use case of “temporarily” disabling a test, 
  with the good intention of 
  resurrecting the test at a later time, 
  each 'style trait' provides 
  a way to 'tag' tests as 'ignored'. 
  For example, 
  in a 'FlatSpec' you can 
  change an 'it' or an 'in' to 'ignore':*/
  //*"Assumptions" should "'assume' allow you to cancel a 'test'" in {
  /*"Assumptions" should "'assume' allow you to cancel a 'test'" ignore {
    val liEm = List.empty
    /*If you want to 
     * 'cancel' the 'test' with a message, 
     * just place the 'message' in the parentheses:
     */

    cancel( "Can't run the test because no internet connection was found" )
    assume( liEm.isDefinedAt( 0 ) )
  }*/
  /*"Expected results" should "'assertResult' as an alternative to 'assert'" in {
    val a = 5
    val b = 2

    fail( "I've got a bad feeling about this" )
    assertResult( 2 ) {
      a - b
    }
  }*/

}