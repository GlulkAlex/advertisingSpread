package advertisingSpread

object testInfixTypes {
  /*'Infix Types'
	An 'infix type' 'T1 op T2' consists of
	an 'infix operator' 'op' which
	gets applied to
	two 'type' operands 'T1' and 'T2'.
	The 'type' is equivalent to
	the 'type application' 'op[T1,T2]'.
	
	The 'infix operator' 'op' may be
	an 'arbitrary identifier',
	except for '*', which is
	reserved as
	a 'postfix modifier' denoting
	a 'repeated parameter type'.
	
	We can make a 'type infix', meaning that
	the 'type' can be
	displayed in complement between two 'types'
	in order to
	make a readable declaration:*/
  case class Person( name: String )
  /*? using 'val' 'param' to create a field within 'class' ?
  instead of 'def' with 'setter' & 'getter'*/
  class Loves[ A, B ]( val a: A,
                       val b: B )

  /*def announceCouple( couple: Person Loves Person ) = {*/
  /*equivalent to:*/
  def announceCouple( couple: Loves[ Person, Person ] ) = {
    //Notice our type: Person loves Person!
    couple.a.name + " is in love with " + couple.b.name
  }                                               //> announceCouple: (couple: advertisingSpread.testInfixTypes.Loves[advertising
                                                  //| Spread.testInfixTypes.Person,advertisingSpread.testInfixTypes.Person])Strin
                                                  //| g

  val romeo = new Person( "Romeo" )               //> romeo  : advertisingSpread.testInfixTypes.Person = Person(Romeo)
  val juliet = new Person( "Juliet" )             //> juliet  : advertisingSpread.testInfixTypes.Person = Person(Juliet)

  announceCouple( new Loves( romeo, juliet ) ) /*should be()*/
                                                  //> res0: String = Romeo is in love with Juliet
  /*Of course
	we can
	make this a bit more elegant by
	creating an 'infix operator' method to
	use with our 'infix type':*/
  case class Person2( name: String ) {
    /*? has same type as Loves2 ?*/
    def loves( person: Person2 ) = new Loves2( this, person )

    override def toString = "P._name:" + name
  }

  class Loves2[ A, B ]( val a: A,
                        val b: B ) {
    override def toString = "a:" + a +
      " & b:" + b
  }

  /*? use function as parameter ?*/
  def announceCouple2( couple: Person2 Loves2 Person2 ) = {
    /*Notice our type:
    Person loves Person!*/
    couple.a.name + " is in love with " + couple.b.name
  }                                               //> announceCouple2: (couple: advertisingSpread.testInfixTypes.Loves2[advertisi
                                                  //| ngSpread.testInfixTypes.Person2,advertisingSpread.testInfixTypes.Person2])S
                                                  //| tring

  val romeo2 = new Person2( "Romeo" )             //> romeo2  : advertisingSpread.testInfixTypes.Person2 = P._name:Romeo
  val juliet2 = new Person2( "Juliet" )           //> juliet2  : advertisingSpread.testInfixTypes.Person2 = P._name:Juliet

  announceCouple2( romeo2 loves juliet2 ) /*should be()*/
                                                  //> res1: String = Romeo is in love with Juliet
  announceCouple2( romeo2.loves( juliet2 ) ) /*should be()*/
                                                  //> res2: String = Romeo is in love with Juliet
  /*arguments needed,
  no default empty companion*/
  val lvs2 = new Loves2( 1, 2 )                   //> lvs2  : advertisingSpread.testInfixTypes.Loves2[Int,Int] = a:1 & b:2
  romeo2.loves( juliet2 )                         //> res3: advertisingSpread.testInfixTypes.Loves2[advertisingSpread.testInfixTy
                                                  //| pes.Person2,advertisingSpread.testInfixTypes.Person2] = a:P._name:Romeo & b
                                                  //| :P._name:Juliet
}