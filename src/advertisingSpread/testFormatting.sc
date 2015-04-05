package advertisingSpread

object testFormatting {
  /*'Character Literals' can be
	an 'escape sequence',
	including 'octal' or 'hexidecimal':*/
  val c = '\u0061' //unicode for a                //> c  : Char = a
  /*depricated
  val d = '\141'*/ /*octal for a*/
  val e = '\"'                                    //> e  : Char = "
  val f = '\\'                                    //> f  : Char = \

  "%c".format( c ) /*should be()*/                //> res0: String = a
  /*"%c".format(d)*/ /*should be()*/
  "%c".format( e ) /*should be()*/                //> res1: String = "
  "%c".format( f ) /*should be()*/                //> res2: String = \

  /*Formatting can also include numbers:*/
  val j = 190                                     //> j  : Int = 190
  "%d bottles of beer on the wall" format j - 100 /*should be ()*/
                                                  //> res3: String = 90 bottles of beer on the wall
  /*Formatting can be used for
	any number of items,
	like a string and a number:*/
  /*val j = 190*/
  val k = "vodka"                                 //> k  : String = vodka

  "%d bottles of %s on the wall".format( j - 100, k ) /*should be ()*/
                                                  //> res4: String = 90 bottles of vodka on the wall

}