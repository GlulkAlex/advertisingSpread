package advertisingSpread

object variableDeclarationTest {
  /*Example

	The following example shows
	how 'properties' can be simulated in Scala.
	It defines
	a 'class' 'TimeOfDayVar' of
	'time' values with
	updatable integer 'fields' representing
	hours, minutes, and seconds.
	Its implementation contains
	'tests' that allow only
	'legal values' to be
	assigned to these 'fields'.
	The user code,
	on the other hand,
	accesses these 'fields' just like 'normal variables'.*/
  class TimeOfDayVar {
    private var h: Int = 0
    private var m: Int = 0
    private var s: Int = 0

    /*a 'getter' function 'x' which
    returns the value currently assigned to the variable, as well as
    a 'setter' function 'x_=' which
    changes the value currently assigned to the variable.
    The functions have the 'same signatures' as
    for a variable declaration.
    The template then
    has these 'getter' and 'setter' functions as 'members',
    whereas the original variable cannot be
    accessed directly as a template member.*/
    def hours = h
    def hours_=( h: Int ) = if ( 0 <= h && h < 24 ) {
	    this.h = h
    }
    else {
	    throw new Exception /*java.util.*/ /*DateError()*/
    }

    def minutes = m
    def minutes_=( m: Int ) = if ( 0 <= m && m < 60 ) {
	    this.m = m
    }    else {
	    throw new /*java.lang.*/Exception /*DateError()*/
    }

    def seconds = s
    def seconds_=( s: Int ) = if ( 0 <= s && s < 60 ) {
	    this.s = s
    }
    else {
	    throw new IndexOutOfBoundsException /*DateError()*/
    }
    
    override def toString = "h:" + h + "-m:" + m + "-s:" + s
  }
  
  val d = new TimeOfDayVar                        //> d  : advertisingSpread.variableDeclarationTest.TimeOfDayVar = h:0-m:0-s:0
  
  d.hours = 8;
  d.minutes = 30;
  d.seconds = 0
  d                                               //> res0: advertisingSpread.variableDeclarationTest.TimeOfDayVar = h:8-m:30-s:0
                                                  //| 
  d.hours = 25 /* throws a DateError exception*/  //> java.lang.Exception
                                                  //| 	at advertisingSpread.variableDeclarationTest$TimeOfDayVar.hours_$eq(adve
                                                  //| rtisingSpread.variableDeclarationTest.scala:40)
                                                  //| 	at advertisingSpread.variableDeclarationTest$$anonfun$main$1.apply$mcV$s
                                                  //| p(advertisingSpread.variableDeclarationTest.scala:67)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$$anonfun$$exe
                                                  //| cute$1.apply$mcV$sp(WorksheetSupport.scala:76)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$.redi
                                                  //| Output exceeds cutoff limit.
}