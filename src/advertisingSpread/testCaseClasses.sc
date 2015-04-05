package advertisingSpread

object testCaseClasses {
  /*Case Classes
	Scala supports
	the notion of case classes.
	'Case classes' are
	regular classes which
	export their 'constructor parameters' and
	which provide
	a 'recursive decomposition' mechanism via
	'pattern matching'.
	
	Here is
	an example for
	a class hierarchy which
	consists of
	an 'abstract' 'super class' 'Term' and
	three 'concrete' 'case classes'
	Var, Fun, and App.*/

  abstract class Term
  case class Var( name: String ) extends Term
  case class Fun( arg: String, body: Term ) extends Term
  /*? apply ? or operator ? or relation?*/
  case class App( f: Term, v: Term ) extends Term
  /*This 'class hierarchy' can be used to
	represent terms of the 'untyped' 'lambda calculus'.
	To facilitate the construction of 'case class' 'instances',
	Scala does not require that
	the 'new' primitive is used.
	One can simply use the 'class name' as a 'function'.
	
	Here is an example:*/

  Fun( "x", Fun( "y", App( Var( "x" ), Var( "y" ) ) ) )
                                                  //> res0: advertisingSpread.testCaseClasses.Fun = Fun(x,Fun(y,App(Var(x),Var(y)
                                                  //| )))
  /*The 'constructor parameters' of 'case classes' are
	treated as
	'public values' and
	can be accessed directly.*/

  val x = Var( "x" )                              //> x  : advertisingSpread.testCaseClasses.Var = Var(x)

  Console.println( x.name )                       //> x

  /*For every 'case class'
  the Scala 'compiler' generates
  'equals method' which
  implements
  'structural equality' and
  a 'toString method'.
  For instance:*/

  val x1 = Var( "x" )                             //> x1  : advertisingSpread.testCaseClasses.Var = Var(x)
  val x2 = Var( "x" )                             //> x2  : advertisingSpread.testCaseClasses.Var = Var(x)
  val y1 = Var( "y" )                             //> y1  : advertisingSpread.testCaseClasses.Var = Var(y)

  println( "" + x1 + " == " + x2 + " => " + ( x1 == x2 ) )
                                                  //> Var(x) == Var(x) => true
  println( "" + x1 + " == " + y1 + " => " + ( x1 == y1 ) )
                                                  //> Var(x) == Var(y) => false
  /*will print
		Var(x) == Var(x) => true
		Var(x) == Var(y) => false*/

  /*It makes only sense to
	define 'case classes' if
	'pattern matching' is used to
	decompose 'data structures'.
	The following object defines
	a 'pretty printer' function for
	our 'lambda calculus' representation:*/

  object TermTest /*extends Application*//*extends App*/ {
    def printTerm( term: Term ) {
      term match {
        case Var( n ) =>
          print( n )
        case Fun( x, b ) =>
          print( "^" + x + "." )
          printTerm( b )
        case App( f, v ) =>
          Console.print( "(" )
          printTerm( f )
          print( " " )
          printTerm( v )
          print( ")" )
      }
    }
    def isIdentityFun( term: Term ): Boolean = term match {
      case Fun( x, Var( y ) ) if x == y => true
      case _                            => false
    }
  }

  val id = Fun( "x", Var( "x" ) )                 //> id  : advertisingSpread.testCaseClasses.Fun = Fun(x,Var(x))
  val t = Fun( "x", Fun( "y", App( Var( "x" ), Var( "y" ) ) ) )
                                                  //> t  : advertisingSpread.testCaseClasses.Fun = Fun(x,Fun(y,App(Var(x),Var(y))
                                                  //| ))

  TermTest.printTerm( t )                         //> ^x.^y.(x y)
  println                                         //> 
  println( TermTest.isIdentityFun( id ) )         //> true
  println( TermTest.isIdentityFun( t ) )          //> false
  /*In our example,
	the function 'print' is
	expressed as
	a 'pattern matching' statement
	starting with
	the 'match' keyword and
	consisting of
	sequences of 'case Pattern => Body' 'clauses'.
	
	The program above also defines
	a function 'isIdentityFun' which
	checks
	if a given 'term' corresponds to
	a simple 'identity' function.
	This example uses
	'deep patterns' and 'guards'.
	After matching
	a 'pattern' with a given value,
	the 'guard' (defined after the keyword if) is evaluated.
	If it returns 'true',
	the 'match' succeeds;
	otherwise,
	it fails and the next pattern will be tried.*/

  /*'Case classes' have
  an automatic 'equals method' that works:*/

  case class Person( first: String, last: String )

  val p1 = new Person( "Fred", "Jones" )          //> p1  : advertisingSpread.testCaseClasses.Person = Person(Fred,Jones)
  val p2 = new Person( "Shaggy", "Rogers" )       //> p2  : advertisingSpread.testCaseClasses.Person = Person(Shaggy,Rogers)
  val p3 = new Person( "Fred", "Jones" )          //> p3  : advertisingSpread.testCaseClasses.Person = Person(Fred,Jones)

  ( p1 == p2 ) /*should be()*/                    //> res1: Boolean = false
  ( p1 == p3 ) /*should be()*/                    //> res2: Boolean = true

  ( p1 eq p2 ) /*should be()*/                    //> res3: Boolean = false
  ( p1 eq p3 ) /*should be()*/ /* not identical, merely equal*/
                                                  //> res4: Boolean = false
  /*'Case classes' have
  an automatic 'hashcode' method that works:*/
  /*case class Person(first: String, last: String)*/

  val p11 = new Person( "Fred", "Jones" )         //> p11  : advertisingSpread.testCaseClasses.Person = Person(Fred,Jones)
  val p22 = new Person( "Shaggy", "Rogers" )      //> p22  : advertisingSpread.testCaseClasses.Person = Person(Shaggy,Rogers)
  val p33 = new Person( "Fred", "Jones" )         //> p33  : advertisingSpread.testCaseClasses.Person = Person(Fred,Jones)

  ( p11.hashCode == p22.hashCode ) /*should be()*///> res5: Boolean = false
  ( p11.hashCode == p33.hashCode ) /*should be()*///> res6: Boolean = true

  /*'Case classes' have
  a convenient way they can be created:*/
  case class Dog( name: String, breed: String )

  val d1 = Dog( "Scooby", "Doberman" )            //> d1  : advertisingSpread.testCaseClasses.Dog = Dog(Scooby,Doberman)
  val d2 = Dog( "Rex", "Custom" )                 //> d2  : advertisingSpread.testCaseClasses.Dog = Dog(Rex,Custom)
  val d3 = new Dog( "Scooby", "Doberman" ) /* the old way of creating using 'new'*/
                                                  //> d3  : advertisingSpread.testCaseClasses.Dog = Dog(Scooby,Doberman)

  ( d1 == d3 ) /*should be()*/                    //> res7: Boolean = true
  ( d1 == d2 ) /*should be()*/                    //> res8: Boolean = false
  ( d2 == d3 ) /*should be()*/                    //> res9: Boolean = false
  /*'Case classes' have
  a convenient 'toString' method defined:*/
  /*case class Dog(name: String, breed: String)
  val d1 = Dog("Scooby", "Doberman")*/
  d1.toString /*should be()*/                     //> res10: String = Dog(Scooby,Doberman)
  /*Case classes have 'automatic properties':*/
  /*case class Dog( name: String, breed: String )

  val d1 = Dog( "Scooby", "Doberman" )*/
  d1.name /*should be()*/                         //> res11: String = Scooby
  d1.breed /*should be()*/                        //> res12: String = Doberman
  /*'Case classes' can have 'mutable properties':*/
  case class Dogy( var name: String, breed: String )
  /* you can rename a dog, but
  change its breed? nah!*/
  val d_1 = Dogy( "Scooby", "Doberman" )          //> d_1  : advertisingSpread.testCaseClasses.Dogy = Dogy(Scooby,Doberman)

  d_1.name /*should be()*/                        //> res13: String = Scooby
  d_1.breed /*should be()*/                       //> res14: String = Doberman

  d_1.name = "Scooby Doo" /* but is it a good idea?*/

  d_1.name /*should be()*/                        //> res15: String = Scooby Doo
  d_1.breed /*should be()*/                       //> res16: String = Doberman
  /*Safer alternatives exist
  for 'altering' 'case classes':*/
  case class Dogsy( name: String,
                    breed: String ) /* Doberman*/

  val d11 = Dogsy( "Scooby", "Doberman" )         //> d11  : advertisingSpread.testCaseClasses.Dogsy = Dogsy(Scooby,Doberman)

  val d22 = d11
    .copy( name = "Scooby Doo" ) /* copy the 'case class' but
                                                  //> d22  : advertisingSpread.testCaseClasses.Dogsy = Dogsy(Scooby Doo,Doberman)
                                                  //| 
  change the name in the copy*/

  d11.name /*should be()*/ /* original left alone*/
                                                  //> res17: String = Scooby
  d11.breed /*should be()*/                       //> res18: String = Doberman

  d22.name /*should be()*/                        //> res19: String = Scooby Doo
  d22.breed /*should be()*/ /* copied from the original*/
                                                  //> res20: String = Doberman
  /*'Case classes' can have
'default' and 'named' parameters:*/
  case class Person2( first: String,
                      last: String,
                      age: Int = 0,
                      ssn: String = "" )

  val p10 = Person2( "Fred",
    "Jones",
    23,
    "111-22-3333" )                               //> p10  : advertisingSpread.testCaseClasses.Person2 = Person2(Fred,Jones,23,11
                                                  //| 1-22-3333)
  val p20 = Person2( "Samantha",
    "Jones" ) /* note: missing 'age' and 'ssn'*/  //> p20  : advertisingSpread.testCaseClasses.Person2 = Person2(Samantha,Jones,0
                                                  //| ,)
  val p30 = Person2( last = "Jones",
    first = "Fred",
    ssn = "111-22-3333" ) /* note: the order can change, and missing 'age'*/
                                                  //> p30  : advertisingSpread.testCaseClasses.Person2 = Person2(Fred,Jones,0,111
                                                  //| -22-3333)
  val p40 = p30.copy( age = 23 )                  //> p40  : advertisingSpread.testCaseClasses.Person2 = Person2(Fred,Jones,23,11
                                                  //| 1-22-3333)

  p10.first /*should be()*/                       //> res21: String = Fred
  p10.last /*should be()*/                        //> res22: String = Jones
  p10.age /*should be()*/                         //> res23: Int = 23
  p10.ssn /*should be()*/                         //> res24: String = 111-22-3333

  p20.first /*should be()*/                       //> res25: String = Samantha
  p20.last /*should be()*/                        //> res26: String = Jones
  p20.age /*should be()*/                         //> res27: Int = 0
  p20.ssn /*should be()*/                         //> res28: String = ""

  p30.first /*should be()*/                       //> res29: String = Fred
  p30.last /*should be()*/                        //> res30: String = Jones
  p30.age /*should be()*/                         //> res31: Int = 0
  p30.ssn /*should be()*/                         //> res32: String = 111-22-3333

  ( p10 == p40 ) /*should be()*/                  //> res33: Boolean = true

  /*'Case classes' can be
  disassembled to
  their constituent parts as a 'tuple'(like 'record'):*/
  val p100 = Person2( "Fred", "Jones", 23, "111-22-3333" )
                                                  //> p100  : advertisingSpread.testCaseClasses.Person2 = Person2(Fred,Jones,23,1
                                                  //| 11-22-3333)

  val parts = Person2
    .unapply( p100 )
    .get /* this seems weird, but                 //> parts  : (String, String, Int, String) = (Fred,Jones,23,111-22-3333)
  it's critical to other features of Scala*/

  parts._1 /*should be()*/                        //> res34: String = Fred
  /*value not a member
  p100._1*/
  p100.first                                      //> res35: String = Fred
  parts._2 /*should be()*/                        //> res36: String = Jones
  parts._3 /*should be()*/                        //> res37: Int = 23
  parts._4 /*should be()*/                        //> res38: String = 111-22-3333
  /*'Case classes' are 'Serializable'*/
  case class PersonCC( firstName: String, lastName: String )
  val indy = PersonCC( "Indiana", "Jones" )       //> indy  : advertisingSpread.testCaseClasses.PersonCC = PersonCC(Indiana,Jones
                                                  //| )

  indy.isInstanceOf[ Serializable ] /*should be()*/
                                                  //> res39: Boolean = true

  class Person3( firstName: String, lastName: String )
  val junior = new Person( "Indiana", "Jones" )   //> junior  : advertisingSpread.testCaseClasses.Person = Person(Indiana,Jones)
                                                  //| 

  junior.isInstanceOf[ Serializable ] /*should be()*/
                                                  //> res40: Boolean = true

}