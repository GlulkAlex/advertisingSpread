package advertisingSpread

object testImplicits {
  /*Implicits
	A method with
	'implicit parameters' can be
	applied to
	arguments just like
	a normal method.
	In this case
	the 'implicit label' has no effect.
	However,
	if such a method misses arguments for its 'implicit parameters',
	such arguments will be automatically provided.
	
	The actual arguments that are
	eligible to be passed to
	an implicit parameter fall into two categories:
	First,
	eligible are all identifiers 'x' that can be
	accessed at the point of the 'method call' without
	a prefix and
	that denote
	an 'implicit definition' or
	an 'implicit parameter'.
	Second,
	eligible are also
	all members of 'companion modules' of
	the implicit parameter’s 'type' that are
	labeled 'implicit'.
	
	In the following example
	we define
	a method 'sum' which computes
	the sum of a list of elements using
	the monoid’s
	'add' and
	'unit' operations.
	Please note that:
	 'implicit values' can not be top-level,
	 they have to be
 members of a template.*/
  abstract class SemiGroup[ A ] {
    def add( x: A,
             y: A ): A
  }
  abstract class Monoid[ A ] extends SemiGroup[ A ] {
    def unit: A
  }

  object ImplicitTest /*extends App*/ /*?not work for sheets?*/ {
    implicit object StringMonoid extends Monoid[ String ] {
      def add( x: String,
               y: String ): String = x concat y
      def unit: String = ""
    }
    implicit object IntMonoid extends Monoid[ Int ] {
      def add( x: Int,
               y: Int ): Int = x + y
      def unit: Int = 0
    }
    /*? curring ?*/
    def sum[ A ]( xs: List[ A ] )( implicit m: Monoid[ A ] ): A =
      if ( xs.isEmpty ) m.unit
      else m.add( xs.head, sum( xs.tail ) )

    def testItOut: Unit = {
      println( sum( List( 1, 2, 3 ) ) )
      println( sum( List( "a", "b", "c" ) ) )
      /*output from Scala interpreter
    6
    abc*/
    }
  }
  /*import ImplicitTest._*/
  ImplicitTest.testItOut                          //> 6
                                                  //| abc
  1 /*main sheet purpose is evaluate and show result
                                                  //> res0: Int(1) = 1
  so let it do its work
  give it some expression to process*/

  /*'Implicits' wrap around
	existing classes to
	provide extra functionality.
	This is similar to
	'monkey patching' in Ruby, and
	Meta-Programming in Groovy.
	
	Creating a method 'isOdd' for Int,
	which doesn't exist:*/
  class KoanIntWrapper( val original: Int ) {
    def isOdd = original % 2 != 0
  }
  /*? like last resort for expression evaluation
  if no such method defined then look for 'implicit' that may fit
  ?*/
  /** in JS:
    * With 'call()' or 'apply()' you can
    * set the value of 'this', and
    * invoke a function as
    * a 'new method' of an existing object.
    */

  /** Warper,
    * object warper
    */
  implicit def thisMethodNameIsIrrelevant( value: Int ) = new KoanIntWrapper( value )
                                                  //> thisMethodNameIsIrrelevant: (value: Int)advertisingSpread.testImplicits.Koa
                                                  //| nIntWrapper

  def isThisOdd( someObject: Int ) = someObject % 2 != 0
                                                  //> isThisOdd: (someObject: Int)Boolean

  19.isOdd /*should be()*/                        //> res1: Boolean = true
  20.isOdd /*should be()*/                        //> res2: Boolean = false
  /*20 isOdd*/
  /*isOdd 77*/
  isThisOdd( 33 )                                 //> res3: Boolean = true
  /*isThisOdd 33
  33 isThisOdd*/

  /*'Implicits rules' can be
  'imported' into your 'scope' with an 'import':*/
  object MyPredef {

    class KoanIntWrapper( val original: Int ) {
      def isOdd2 = original % 2 != 0

      def isEven = !isOdd2
    }

    implicit def thisMethodNameIsIrrelevant( value: Int ) = new KoanIntWrapper( value )
  }

  import MyPredef._
  /*imported 'implicits' come into effect within this 'scope'*/
  /*work in Scala interpreter
  19.isOdd2 /*should be()*/
  20.isEven /*should be()*/*/

  /*'Implicits' can be used to
automatically 'convert' one type to another*/
  import java.math.BigInteger
  implicit def Int2BigIntegerConvert( value: Int ): BigInteger = new BigInteger( value.toString )
                                                  //> Int2BigIntegerConvert: (value: Int)java.math.BigInteger

  def add( a: BigInteger,
           b: BigInteger ) = a.add( b )           //> add: (a: java.math.BigInteger, b: java.math.BigInteger)java.math.BigInteger
                                                  //| 

  add( Int2BigIntegerConvert( 3 ),
    Int2BigIntegerConvert( 6 ) ) == Int2BigIntegerConvert( 9 ) /*should be()*/
                                                  //> res4: Boolean = true

  add( 3, 6 ) == 9 /*should be()*/                //> res5: Boolean = false
  add( 3, 6 ) == Int2BigIntegerConvert( 9 ) /*should be()*/
                                                  //> res6: Boolean = true

  add( 3, 6 ) == ( 9: BigInteger ) /*should be()*///> res7: Boolean = true
  add( 3, 6 ).intValue == 9 /*should be()*/       //> res8: Boolean = true

  /*'Implicits' can be used (to)
  declare
  a value to be provided as
  a 'default' as long as
  an 'implicit value' is set with in the scope.
  These are called 'implicit function parameters':*/
  def howMuchCanIMake_?( hours: Int )( implicit dollarsPerHour: BigDecimal ) =
    dollarsPerHour * hours                        //> howMuchCanIMake_? : (hours: Int)(implicit dollarsPerHour: BigDecimal)scala.
                                                  //| math.BigDecimal

  implicit var hourlyRate = BigDecimal( 34.00 )   //> hourlyRate  : scala.math.BigDecimal = 34.0

  howMuchCanIMake_?( 30 ) /*should be()*/         //> res9: scala.math.BigDecimal = 1020.0

  hourlyRate                                      //> res10: scala.math.BigDecimal = 34.0
  hourlyRate = BigDecimal( 95.00 )
  hourlyRate                                      //> res11: scala.math.BigDecimal = 95.0
  howMuchCanIMake_?( 95 ) /*should be()*/         //> res12: scala.math.BigDecimal = 9025.0

  /*'Implicit Function Parameters' can contain
  a list of 'implicits':*/
  def howMuchCanIMake( hours: Int )( implicit amount: BigDecimal,
                                     currencyName: String ) =
    ( amount * hours ).toString() + " " + currencyName
                                                  //> howMuchCanIMake: (hours: Int)(implicit amount: BigDecimal, implicit currenc
                                                  //| yName: String)String

  /*?same type implicits conflict with each other
  or too ambiguous?
  implicit var hourlyRate2 = BigDecimal( 34.00 )*/
  implicit val currencyName = "Dollars"           //> currencyName  : String = Dollars

  34 * 30                                         //> res13: Int(1020) = 1020
  howMuchCanIMake( 30 ) /*should be()*/           //> res14: String = 2850.0 Dollars

  hourlyRate = BigDecimal( 95.00 )
  howMuchCanIMake( 95 ) /*should be()*/           //> res15: String = 9025.0 Dollars

  /*'Default arguments' though are preferred to
  'Implicit Function Parameters'*/
  /*? not pollute the scope ?*/
  def howMuchCanIMake3( hours: Int,
                        amount: BigDecimal = 34,
                        currencyName: String = "Dollars" ) =
    ( amount * hours )
      .toString() + " " + currencyName            //> howMuchCanIMake3: (hours: Int, amount: BigDecimal, currencyName: String)Str
                                                  //| ing

  howMuchCanIMake3( 30 ) /*should be()*/          //> res16: String = 1020 Dollars

  howMuchCanIMake3( 95, 95 ) /*should be()*/      //> res17: String = 9025 Dollars
}