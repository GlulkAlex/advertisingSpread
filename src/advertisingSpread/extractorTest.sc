package advertisingSpread

object extractorTest {
  /*What's an extractor?
In Scala it's
a method in 'any object' called 'unapply', and
that method is used to
disassemble the object given by
returning a 'tuple' wrapped in an 'option'.
Extractors can be used to assign values:*/
  class Car( val make: String, val model: String, val year: Short, val topSpeed: Short )

  /*do not get in
'object' & 'val' with same names
if there be 'class' then object be 'companion'*/
  /*? object constructor ?*/
  object ChopShop {
    /*like template or pattern
  for creating new val's*/
    def unapply( x: Car ) = Some( x.make, x.model, x.year, x.topSpeed )
  }

  /*? object preservs / store patemeters ?
?or extract parameters values?
but with new val same as in 'class' for 'unapply'?*/
  /*looks like function for creating new val's of type
described in class with name passed as parameters*/
  val ChopShop( a, b, c, d ) = new Car( "Chevy", "Camaro", 1978, 120 )
                                                  //> a  : String = Chevy
                                                  //| b  : String = Camaro
                                                  //| c  : Short = 1978
                                                  //| d  : Short = 120
  val ChopShop( a2, b2, c2, d2 ) = new Car( "BMW", "model5", 1976, 180 )
                                                  //> a2  : String = BMW
                                                  //| b2  : String = model5
                                                  //| c2  : Short = 1976
                                                  //| d2  : Short = 180
  val ChopShop( a3 ) = new Car( "BMW", "model5", 1976, 180 )
                                                  //> a3  : (String, String, Short, Short) = (BMW,model5,1976,180)
  ChopShop                                        //> res0: advertisingSpread.extractorTest.ChopShop.type = advertisingSpread.ext
                                                  //| ractorTest$ChopShop$@1b64c45
  /*one or all parameters needed
val ChopShop(a4,b4) = new Car("BMW", "model5", 1976, 180)*/

  a /*should be()*/                               //> res1: String = Chevy
  b /*should be()*/                               //> res2: String = Camaro
  c /*should be()*/                               //> res3: Short = 1978
  d /*should be()*/                               //> res4: Short = 120
  /*What is typical is to
create a 'custom extractor' in
the 'companion object' of the 'class'.
In this exercise, we use it as an assignment:*/
  class Employee( val firstName: String,
                  val middleName: Option[ String ],
                  val lastName: String )

  object Employee {
    //factory methods, extractors, apply
    //Extractor: Create tokens that represent your object
    def unapply( x: Employee ) =
      /*?swap parameters?*/
      Some( x.lastName, x.middleName, x.firstName )
  }

  val singri = new Employee( "Singri", None, "Keerthi" )
                                                  //> singri  : advertisingSpread.extractorTest.Employee = advertisingSpread.extr
                                                  //| actorTest$$anonfun$main$1$Employee$2@11d970
  /*?swap parameters?*/
  val Employee( a5, b5, c5 ) = singri             //> a5  : String = Keerthi
                                                  //| b5  : Option[String] = None
                                                  //| c5  : String = Singri
  val result = singri match {
    case Employee( "Singri", None, x )      => "Yay, Singri %s! with no middle name!".format( x )
    case Employee( "Singri", Some( x ), _ ) => "Yay, Singri with a middle name of %s".format( x )
    case _                                  => "I don't care, going on break"
  }                                               //> result  : String = I don't care, going on break
}