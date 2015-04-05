package advertisingSpread

object InfixPrefixPostfixOperators {
  /*Infix, Prefix and Postfix Operators
	Any method which takes
	a 'single parameter' can be used as
	an 'infix operator':
	'a.m(b)' can be written 'a m b'.*/
  val g: Int = 3                                  //> g  : Int = 3

  ( g + 4 ) /*/*should be()*/*/ /* + is an infix operator*/
                                                  //> res0: Int = 7
  g.+( 4 ) /*/*should be()*/*/ /* same result but not using the infix operator*/
                                                  //> res1: Int = 7
  /*'Infix Operators' do NOT work if
an object has
a method that takes 'two parameters':*/
  val g1: String = "Check out the big brains on Brad!"
                                                  //> g1  : String = Check out the big brains on Brad!

  g1 indexOf 'o' /*/*should be()*/ //indexOf(Char) can be used as an infix operator*/
                                                  //> res2: Int = 6

  /* g1 indexOf 'o', 4 should be (6) //indexOf(Char, Int) cannot be used an infix operator*/

  g1.indexOf( 'o', 7 ) /*/*should be()*/ //indexOf(Char, Int) must use standard java/scala calls*/
                                                  //> res3: Int = 25
  /*Any method which
	does not require a parameter' can be used as
	a 'postfix operator':
	'a.m' can be written 'a m'.
	
	For instance 'a.##(b)' can be written
	'a ## b' and
	'a.!' can be written 'a!'
	
	Postfix operators have 'lower precedence' than 'infix operators', so:
	  'foo bar baz' means 'foo.bar(baz)'.
	  'foo bar baz bam' means '(foo.bar(baz)).bam'
	  'foo bar baz bam bim' means '(foo.bar(baz)).bam(bim)'.*/
  val g3: Int = 31                                //> g3  : Int = 31
  ( g3 toHexString )                              //> res4: String = 1f
  /*/*should be()*/*/ /*'toHexString' takes no params therefore
	can be called as a 'postfix operator'.
	Hint: The answer is "1f"*/

  /*'Prefix' operators work if
	an object has
	a method name that starts with 'unary_' :*/
  ( -g3 ) /*/*should be()*/*/                     //> res5: Int = -31

  /*Here
  we create our own 'prefix operator' for
  our own 'class'.
  The only 'identifiers' that can be used
  as 'prefix operators' are
  '+', '-', '!', and '~':*/
  class Stereo {
    def unary_+ = "on"

    def unary_- = "off"
  }

  val stereo = new Stereo                         //> stereo  : advertisingSpread.InfixPrefixPostfixOperators.Stereo = advertisin
                                                  //| gSpread.InfixPrefixPostfixOperators$$anonfun$main$1$Stereo$1@c7da1e
  ( +stereo ) /*should be()*/                     //> res6: String = on
  ( -stereo ) /*should be()*/                     //> res7: String = off
}