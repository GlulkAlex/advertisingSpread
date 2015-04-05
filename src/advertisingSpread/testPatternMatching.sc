package advertisingSpread

object testPatternMatching {
  /*'Pattern matching' can 'wildcard' parts of expressions:*/
  def goldilocks( expr: Any ) = expr match {
    case ( "porridge", _ )   => "eating"
    case ( "chair", "Mama" ) => "sitting"
    case ( "bed", "Baby" )   => "sleeping"
    case _                   => "what?"
  }                                               //> goldilocks: (expr: Any)String

  goldilocks( ( "porridge", "Papa" ) ) /*/*should be()*/*/
                                                  //> res0: String = eating
  goldilocks( ( "chair", "Mama" ) ) /*/*should be()*/*/
                                                  //> res1: String = sitting
  /*'Pattern' matching can done on
  'regular expression' 'groups':*/
  val EatingRegularExpression = """Eating Alert: bear=([^,]+),\s+source=(.+)""".r /*
                                                  //> EatingRegularExpression  : scala.util.matching.Regex = Eating Alert: bear=([
                                                  //| ^,]+),\s+source=(.+)
  '.r' turns a String to a 'regular expression'*/
  val SittingRegularExpression = """Sitting Alert: bear=([^,]+),\s+source=(.+)""".r
                                                  //> SittingRegularExpression  : scala.util.matching.Regex = Sitting Alert: bear=
                                                  //| ([^,]+),\s+source=(.+)
  val SleepingRegularExpression = """Sleeping Alert: bear=([^,]+),\s+source=(.+)""".r
                                                  //> SleepingRegularExpression  : scala.util.matching.Regex = Sleeping Alert: bea
                                                  //| r=([^,]+),\s+source=(.+)

  def goldilocks2( expr: String ) = expr match {
    case (
      EatingRegularExpression( bear,
        source ) ) => "%s said someone's been eating my %s"
      .format( bear, source )
    case (
      SittingRegularExpression( bear,
        source ) ) => "%s said someone's been sitting on my %s"
      .format( bear, source )
    case (
      SleepingRegularExpression( bear,
        source ) ) => "%s said someone's been sleeping in my %s"
      .format( bear, source )
    case _ => "what?"
  }                                               //> goldilocks2: (expr: String)String

  goldilocks2( "Eating Alert: bear=Papa, source=porridge" ) /*should be()*/
                                                  //> res2: String = Papa said someone's been eating my porridge
  goldilocks2( "Sitting Alert: bear=Mama, source=chair" ) /*should be()*/
                                                  //> res3: String = Mama said someone's been sitting on my chair
  /*A backquote '`' can be used to
refer to a 'stable variable' in scope to
create a 'case statement'.
This prevents what is called "Variable Shadowing"*/
  val foodItem = "porridge"                       //> foodItem  : String = porridge

  def goldilocks3( expr: Any ) = expr match {
    case ( `foodItem`, _ )   => "eating"
    case ( "chair", "Mama" ) => "sitting"
    case ( "bed", "Baby" )   => "sleeping"
    case _                   => "what?"
  }                                               //> goldilocks3: (expr: Any)String

  goldilocks3( ( "porridge", "Papa" ) ) /*should be()*/
                                                  //> res4: String = eating
  goldilocks3( ( "chair", "Mama" ) ) /*should be()*/
                                                  //> res5: String = sitting
  goldilocks3( ( "porridge", "Cousin" ) ) /*should be()*/
                                                  //> res6: String = eating
  goldilocks3( ( "beer", "Cousin" ) ) /*should be()*/
                                                  //> res7: String = what?
  /*A backquote '`' can be used to
refer to a 'method parameter' as
a 'stable variable' to
create a 'case statement':*/
  def patternEquals( i: Int,
                     j: Int ) = j match {
    case `i` => true
    case _   => false
  }                                               //> patternEquals: (i: Int, j: Int)Boolean
  patternEquals( 3, 3 ) /*should be()*/           //> res8: Boolean = true
  patternEquals( 7, 9 ) /*should be()*/           //> res9: Boolean = false
  patternEquals( 9, 9 ) /*should be()*/           //> res10: Boolean = true

  /*To obtain the second element in List
	you can expand on the 'pattern'.
	Where
	'x' is the first element,
	'y' is the second element, and
	'xs' is the rest:*/
  val secondElement = List( 1, 2, 3 ) match {
    case x :: y :: xs => y
    case _            => 0
  }                                               //> secondElement  : Int = 2

  secondElement /*should be()*/                   //> res11: Int = 2
}