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
  "Assumptions" should "'assume' allow you to cancel a 'test'" ignore {
    val liEm = List.empty
    /*If you want to 
     * 'cancel' the 'test' with a message, 
     * just place the 'message' in the parentheses:
     */

    cancel( "Can't run the test because no internet connection was found" )
    assume( liEm.isDefinedAt( 0 ) )
  }
  "Expected results" should "'assertResult' as an alternative to 'assert'" in {
    val a = 5
    val b = 2

    fail( "I've got a bad feeling about this" )
    assertResult( 2 ) {
      a - b
    }
  }

}