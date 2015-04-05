package advertisingSpread

object testTraits {
  /*Traits
	Similar to 'interfaces' in Java,
	'traits' are used to
	define 'object types' by
	specifying the 'signature' of the 'supported methods'.
	Unlike Java,
	Scala allows 'traits' to be
	'partially implemented'; i.e. it is possible to
	define 'default implementations' for some methods.
	In contrast to 'classes',
	'traits' may not have 'constructor parameters'.
	
	Here is an example:*/

  trait Similarity {
    def isSimilar( x: Any ): Boolean
    def isNotSimilar( x: Any ): Boolean = !isSimilar( x )
  }
  /*This 'trait' consists of
	two methods
	'isSimilar' and
	'isNotSimilar'.
	While 'isSimilar' does not provide
	a 'concrete method implementation'
	(it is 'abstract' in the terminology of Java),
	method 'isNotSimilar' defines a 'concrete implementation'.
	Consequently,
	'classes' that integrate this 'trait' only have to
	provide a 'concrete implementation' for 'isSimilar'.
	The behavior for 'isNotSimilar' gets
	'inherited' directly from the 'trait'.
	'Traits' are
	typically integrated into a 'class'
	(or other 'traits') with a 'mixin' 'class composition':*/

  class Point( xc: Int,
               yc: Int ) extends Similarity {
    var x: Int = xc
    var y: Int = yc

    def isSimilar( obj: Any ) =
      obj.isInstanceOf[ Point ] &&
        obj.asInstanceOf[ Point ].x == x
  }

  object TraitsTest /*extends*/ /*Application*/ /*Apps*/ {
    val p1 = new Point( 2, 3 )
    val p2 = new Point( 2, 4 )
    val p3 = new Point( 3, 3 )

    println( p1.isNotSimilar( p2 ) )
    println( p1.isNotSimilar( p3 ) )
    println( p1.isNotSimilar( 2 ) )
  }

  TraitsTest                                      //> false
                                                  //| true
                                                  //| true
                                                  //| res0: advertisingSpread.testTraits.TraitsTest.type = advertisingSpread.test
                                                  //| Traits$TraitsTest$@d64342
  /*A 'class' uses
	the 'extends' keyword to
	'mixin' a 'trait' if
	it is the only relationship the 'class' 'inherits':*/
  case class Event( name: String )

  trait EventListener {
    def listen( event: Event ): String
  }
  /*A 'class' can only 'extend' from
	one 'class' or 'trait',
	any 'subsequent extension' should use the keyword 'with':*/
  /*class MyListener extends EventListener {*/
  class OurListener

  class MyListener extends OurListener with EventListener {
    def listen( event: Event ): String =
      event match {
        case Event( "Woodchuck Stampede" ) => "An unfortunate woodchuck stampede occurred"
        case Event( "Moose Stampede" )     => "An unfortunate moose stampede occurred"
        case _                             => "Nothing of importance occurred"
      }
  }

  val evt = Event( "Moose Stampede" )             //> evt  : advertisingSpread.testTraits.Event = Event(Moose Stampede)
  val evt2 = Event( "Woodchuck Stampede" )        //> evt2  : advertisingSpread.testTraits.Event = Event(Woodchuck Stampede)
  val myListener = new MyListener                 //> myListener  : advertisingSpread.testTraits.MyListener = advertisingSpread.t
                                                  //| estTraits$$anonfun$main$1$MyListener$1@1dfb72a

  myListener.listen( evt ) /*should be ()*/       //> res1: String = An unfortunate moose stampede occurred
  myListener.listen( evt2 ) /*should be ()*/      //> res2: String = An unfortunate woodchuck stampede occurred
  /*'Traits' are polymorphic.
  'Any' 'type' can be referred to
  by another 'type' if
  related by extension:*/
  myListener.isInstanceOf[ MyListener ] /*/*should be()*/*/
                                                  //> res3: Boolean = true
  myListener.isInstanceOf[ EventListener ] /*/*should be()*/*/
                                                  //> res4: Boolean = true
  myListener.isInstanceOf[ Any ] /*/*should be()*/*/
                                                  //> res5: Boolean = true
  myListener.isInstanceOf[ AnyRef ] /*/*should be()*/*/
                                                  //> res6: Boolean = true
  /*'Traits' can have
	'concrete implementations' that can be
	mixed into 'concrete classes' with it's own state:*/
  /*? JS Closure ?*/
  trait Logging {
    var logCache = List[ String ]()

    def log( value: String ) = {
      logCache = logCache :+ value
      println( value )
    }
  }

  class Welder extends Logging {
    def weld() {
      log( "welding pipe" )
    }
  }

  class Baker extends Logging {
    def bake() = log( "baking cake" )
  }

  val welder = new Welder                         //> welder  : advertisingSpread.testTraits.Welder = advertisingSpread.testTrait
                                                  //| s$$anonfun$main$1$Welder$1@1adae5d
  welder.weld()                                   //> welding pipe

  val baker = new Baker                           //> baker  : advertisingSpread.testTraits.Baker = advertisingSpread.testTraits$
                                                  //| $anonfun$main$1$Baker$1@407d53
  baker.bake()                                    //> baking cake

  welder.logCache.size /*should be()*/            //> res7: Int = 1
  baker.logCache.size /*should be()*/             //> res8: Int = 1
  /*'Traits' are instantiated before a 'classes' instantiation:*/
  var sb = List[ String ]()                       //> sb  : List[String] = List()

  trait T1 {
    /*interesting behavior
    'x' somehow already declared but
    yet not assign,
    so it has 'default value'*/
    sb = sb :+ "In T1: x=%s".format( x )
    val x = 1
    sb = sb :+ "In T1: x=%s".format( x )
  }

  class C1 extends T1 {
    sb = sb :+ "In C1: y=%s".format( y )
    val y = 2
    sb = sb :+ "In C1: y=%s".format( y )
  }

  sb = sb :+ "Creating C1"
  new C1                                          //> res9: advertisingSpread.testTraits.C1 = advertisingSpread.testTraits$$anonf
                                                  //| un$main$1$C1$1@17e1986
  sb = sb :+ "Created C1"

  sb.mkString( ";" ) /*should be()*/              //> res10: String = Creating C1;In T1: x=0;In T1: x=1;In C1: y=0;In C1: y=2;Cre
                                                  //| ated C1
  /*'Traits' are instantiated before a 'classes' instantiation
  from left to right (precedence / order in calass signature):*/
  trait T2 {
    sb = sb :+ "In T2: z=%s".format( z )
    val z = 1
    sb = sb :+ "In T2: z=%s".format( z )
  }

  class C2 extends T1 with T2 {
    sb = sb :+ "In C1: y=%s".format( y )
    val y = 2
    sb = sb :+ "In C1: y=%s".format( y )
  }
  sb = Nil
  sb = sb :+ "Creating C1"
  new C2                                          //> res11: advertisingSpread.testTraits.C2 = advertisingSpread.testTraits$$anon
                                                  //| fun$main$1$C2$1@f67b76
  sb = sb :+ "Created C1"

  sb.mkString( ";" ) /*should be()*/              //> res12: String = Creating C1;In T1: x=0;In T1: x=1;In T2: z=0;In T2: z=1;In 
                                                  //| C1: y=0;In C1: y=2;Created C1
  /*'Instantiations' are tracked and
will not allow
a 'duplicate instantiation'.
Note:
 'T1' extends 'T2', and
 'C1' also extends 'T2', but
 'T2' is only instantiated once:*/
  trait T3 extends T4 {
    sb = sb :+ "In T1: x=%s".format( x )
    val x = 1
    sb = sb :+ "In T1: x=%s".format( x )
  }

  trait T4 {
    sb = sb :+ "In T2: z=%s".format( z )
    val z = 1
    sb = sb :+ "In T2: z=%s".format( z )
  }

  class C3 extends T3 with T4 {
    sb = sb :+ "In C1: y=%s".format( y )
    val y = 2
    sb = sb :+ "In C1: y=%s".format( y )
  }
  sb = Nil
  sb = sb :+ "Creating C1"
  new C3                                          //> res13: advertisingSpread.testTraits.C3 = advertisingSpread.testTraits$$anon
                                                  //| fun$main$1$C3$1@13f5a29
  sb = sb :+ "Created C1"

  sb.mkString( ";" ) /*should be()*/              //> res14: String = Creating C1;In T2: z=0;In T2: z=1;In T1: x=0;In T1: x=1;In 
                                                  //| C1: y=0;In C1: y=2;Created C1
  /*The 'diamond of death' is avoided since
	'instantiations' are
	tracked and
	will not allow 'multiple instantiations':*/

  trait T5 {
    sb = sb :+ "In T1: x=%s".format( x )
    val x = 1
    sb = sb :+ "In T1: x=%s".format( x )
  }

  trait T6 extends T5 {
    sb = sb :+ "In T2: z=%s".format( z )
    val z = 2
    sb = sb :+ "In T2: z=%s".format( z )
  }

  trait T7 extends T5 {
    sb = sb :+ "In T3: w=%s".format( w )
    val w = 3
    sb = sb :+ "In T3: w=%s".format( w )
  }

  class C4 extends T6 with T7 {
    sb = sb :+ "In C1: y=%s".format( y )
    val y = 4
    sb = sb :+ "In C1: y=%s".format( y )
  }
  sb = Nil
  sb = sb :+ "Creating C1"
  new C4                                          //> res15: advertisingSpread.testTraits.C4 = advertisingSpread.testTraits$$anon
                                                  //| fun$main$1$C4$1@1560810
  sb = sb :+ "Created C1"

  sb.mkString( ";" ) /*should be()*/              //> res16: String = Creating C1;In T1: x=0;In T1: x=1;In T2: z=0;In T2: z=2;In 
                                                  //| T3: w=0;In T3: w=3;In C1: y=0;In C1: y=4;Created C1
}