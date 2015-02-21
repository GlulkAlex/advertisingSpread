package advertisingSpread
//import scala.util.matching.Regex

object pathTest {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(121); 
  println("Scala worksheet text output");$skip(41); 
  var myList = Array(1.9, 2.9, 3.4, 3.5);System.out.println("""myList  : Array[Double] = """ + $show(myList ));$skip(38); 

  println("myList(0):" + myList(0));$skip(56); 
  // Finding the largest element
  var max = myList(0);System.out.println("""max  : Double = """ + $show(max ));$skip(84); ;

  for (i <- 1 to (myList.length - 1)) {
    if (myList(i) > max) max = myList(i);
  };$skip(34); 

  println("Max is " + max);$skip(138); ;

  val graph1 = Array(
    (1, 2), //root
    (2, 3),
    (3, 4),
    (3, 7),
    (4, 5), //end
    (4, 6), //end
    (7, 8) //end
    );System.out.println("""graph1  : Array[(Int, Int)] = """ + $show(graph1 ));$skip(131); 

  val graph2 = Array(
    "1 2", //root
    "2 3",
    "3 4",
    "3 7",
    "4 5", //end
    "4 6", //end
    "7 8" //end
    );System.out.println("""graph2  : Array[String] = """ + $show(graph2 ));$skip(40); 
  println("graph1(0) is " + graph1(0));$skip(42); ;
  val (label, value) = /*pair*/ graph1(0);System.out.println("""label  : Int = """ + $show(label ));System.out.println("""value  : Int = """ + $show(value ));$skip(15); val res$0 = 
  graph1(0)._1;System.out.println("""res0: Int = """ + $show(res$0));$skip(15); val res$1 = 
  graph1(0)._2;System.out.println("""res1: Int = """ + $show(res$1));$skip(61); 
  //var (node: Int, leaf: Int)
  var (node, leaf) = (-1, -1);System.out.println("""node  : Int = """ + $show(node ));System.out.println("""leaf  : Int = """ + $show(leaf ));$skip(48); 
  var nodeLeafPair = new Tuple2[Int, Int](0, 0);System.out.println("""nodeLeafPair  : (Int, Int) = """ + $show(nodeLeafPair ));$skip(26); 
  nodeLeafPair = (-1, -1);$skip(15); val res$2 = 
  nodeLeafPair;System.out.println("""res2: (Int, Int) = """ + $show(res$2));$skip(109); val res$3 = 
  //print("Please enter your input : ")
  //val line = Console.readLine//not in sheet
  graph2(0).split(" ");System.out.println("""res3: Array[String] = """ + $show(res$3));$skip(26); val res$4 = 
  graph2(0).split(" ")(1);System.out.println("""res4: String = """ + $show(res$4));$skip(32); val res$5 = 
  graph2(0).split(" ")(1).toInt;System.out.println("""res5: Int = """ + $show(res$5));$skip(305); 
  //java.lang.String cannot be cast to java.lang.Integer or Long or Double or Short                                                Integer
  //graph2(0).split(" ")(1).asInstanceOf[Int]
  /*graph2(0).split(" ")(1).charAt(0)
  //graph2(0).split(" ")(1).charAt(0).toShort*/
  val x = graph2(0).split(" ")(1);System.out.println("""x  : String = """ + $show(x ));$skip(38); val res$6 = 
  //x.toInt //char2int
  "1".length();System.out.println("""res6: Int = """ + $show(res$6));$skip(11); val res$7 = 
  "2".##();System.out.println("""res7: Int = """ + $show(res$7));$skip(12); val res$8 = 
  "3".toSeq;System.out.println("""res8: Seq[Char] = """ + $show(res$8));$skip(14); val res$9 = 
  "4".toFloat;System.out.println("""res9: Float = """ + $show(res$9));$skip(12); val res$10 = 
  "5".toInt;System.out.println("""res10: Int = """ + $show(res$10));$skip(19); val res$11 = 
  graph2 ++ myList;System.out.println("""res11: Array[Any] = """ + $show(res$11));$skip(14); val res$12 = 
  graph2.size;System.out.println("""res12: Int = """ + $show(res$12));$skip(26); val res$13 = 
  graph2(graph2.size - 1);System.out.println("""res13: String = """ + $show(res$13));$skip(14); val res$14 = 
  graph2.last;System.out.println("""res14: String = """ + $show(res$14));$skip(17); val res$15 = 
  graph2.indices;System.out.println("""res15: scala.collection.immutable.Range = """ + $show(res$15));$skip(17); val res$16 = 
  graph2.take(3);System.out.println("""res16: Array[String] = """ + $show(res$16));$skip(17); val res$17 = 
  graph2.drop(3);System.out.println("""res17: Array[String] = """ + $show(res$17));$skip(22); val res$18 = 
  graph2.dropRight(3);System.out.println("""res18: Array[String] = """ + $show(res$18));$skip(55); val res$19 = 
  graph2.dropWhile { x => x.split(" ")(0).toInt == 4 };System.out.println("""res19: Array[String] = """ + $show(res$19));$skip(51); val res$20 = 
  graph2.dropWhile { x => x.split(" ")(0) == "3" };System.out.println("""res20: Array[String] = """ + $show(res$20));$skip(39); val res$21 = 
  graph2.dropWhile { x => x == "3 4" };System.out.println("""res21: Array[String] = """ + $show(res$21));$skip(90); 
  /*to gain duplicate values or group by*/
  val currNode = graph2(2).split(" ")(0).toInt;System.out.println("""currNode  : Int = """ + $show(currNode ));$skip(70); 
  val dub1 = graph2.filter { x => x.split(" ")(0).toInt == currNode };System.out.println("""dub1  : Array[String] = """ + $show(dub1 ));$skip(58); val res$22 = 
  graph2.count { x => x.split(" ")(0).toInt == currNode };System.out.println("""res22: Int = """ + $show(res$22));$skip(15); val res$23 = 
  dub1.isEmpty;System.out.println("""res23: Boolean = """ + $show(res$23));$skip(55); val res$24 = 
  graph2.filterNot { x => x.split(" ")(0).toInt == 4 };System.out.println("""res24: Array[String] = """ + $show(res$24));$skip(50); val res$25 = 
  graph2.find { x => x.split(" ")(1).toInt == 4 };System.out.println("""res25: Option[String] = """ + $show(res$25));$skip(56); val res$26 = 
  graph2.indexWhere { x => x.split(" ")(1).toInt == 4 };System.out.println("""res26: Int = """ + $show(res$26));$skip(18); val res$27 = 
  "0 0" +: graph2;System.out.println("""res27: Array[String] = """ + $show(res$27));$skip(20); val res$28 = 
  graph2 :+ "10 10";System.out.println("""res28: Array[String] = """ + $show(res$28));$skip(57); 
  /*Unit loop*/
  graph2.foreach { x => print(x + ',') };$skip(71); 
  // Print all the array elements
  for (x <- myList) {
    println(x)
  };$skip(12); val res$29 = 
  !true;System.out.println("""res29: Boolean = """ + $show(res$29));$skip(15); val res$30 = 
  graph1.unzip;System.out.println("""res30: (Array[Int], Array[Int]) = """ + $show(res$30));$skip(1411); val res$31 = 
  /**
   * In 'Scala',
   * 'pattern matching' does not appears only
   * after a the function 'match'.
   * You can
   * put 'pattern matching' in
   * any 'closure' '{}' and
   * also in a 'catch block'.
   */
  /*
  You can
  provide your own behavior for 'pattern matching'.
  This is called 'extractor'.
  To do so,
  you have to
  provide your own implementation of
  the 'unapply()' method
  (and/or 'unapplySeq()' for a sequence).
  */
  /*To go further,
	we will use
	this approach with
	tuples
	in order to
	improve our method 'parserArgument()' above:*/
  /**
   * def parseArgument(arg: String, value: Any) = (arg, value) match {
   * case ("-l", lang) => setLanguageTo(lang)
   * case ("-o" | "--optim", n: Int) //'guard condition'
   * if ((0 < n) && (n <= 5)) => setOptimizationLevelTo(n)
   * case ("-o" | "--optim", badLevel) => badOptimizationLevel(badLevel)
   * case ("-h" | "--help", null) => displayHelp()
   * case bad => badArgument(bad)
   * }
   */
  /*Notice
	first
	the use of the operator '|' that
	allows to
	match alternative forms of 'arg' inside the 'tuple'.
	Notice also
	the use of
	two patterns for options '-o' and '--optim'.
	These patterns are
	distinguished by
	the use of a 'guard condition'.
	The 'guard conditions' help you to
	get fine tuned 'pattern matching' when
	'pattern matching' alone is not enough.*/
  for (p <- graph2 if p.split(" ")(1).toInt > 20) yield p;System.out.println("""res31: Array[String] = """ + $show(res$31));$skip(87); val res$32 = 
  for (p <- graph2 if p.split(" ")(1).toInt > graph2.size) yield p.split(" ")(0).toInt;System.out.println("""res32: Array[Int] = """ + $show(res$32));$skip(58); val res$33 = 
  for (p <- graph2 if p.split(" ")(1).toInt == 7) yield p;System.out.println("""res33: Array[String] = """ + $show(res$33));$skip(68); val res$34 = 
  for (p <- graph2 if p.split(" ")(1).toInt <= graph2.size) yield p;System.out.println("""res34: Array[String] = """ + $show(res$34));$skip(16); val res$35 = 
  graph2.length;System.out.println("""res35: Int = """ + $show(res$35));$skip(124); val res$36 = 
  /*combination of sum operands equal 'x'*/
  for {
    i <- 1 to 8
    j <- 1 until 8
    if (i + j == 8)
  } yield (i, j);System.out.println("""res36: scala.collection.immutable.IndexedSeq[(Int, Int)] = """ + $show(res$36));$skip(20); 
  val sPair = "1 2";System.out.println("""sPair  : String = """ + $show(sPair ));$skip(342); 
  /*r( ) method.
  Scala implicitly converts the 'String' to
  a 'RichString' and
  invokes that method to get an instance of 'Regex'
  */
  //*val pattern = "\\d\\s\\d".r//*work
  //*val pattern = "\\d".r//*work
  //*val pattern = "^\\d".r//*work
  //*val pattern = "\\d$".r //*work
  val pattern = "(\\d)\\s(\\d)".r("node", "leaf");System.out.println("""pattern  : scala.util.matching.Regex = """ + $show(pattern ));$skip(66); val res$37 =  //*work
  //*(pattern findAllIn sPair).mkString(",")
  pattern.toString();System.out.println("""res37: String = """ + $show(res$37));$skip(655); 
  //pattern._1
  /*'Regex' in 'case pattern matching'*/
  /*There are two ways of
  using a 'Regex' to find a pattern:
  calling methods on 'Regex', such as
  'findFirstIn' or 'findAllIn', or
  using it as an 'extractor' in a 'pattern match'.
  */
  //*method on 'Regex' -- matters
  //*for Option[String] use 'findFirstMatchIn'
  val (node1: Int, leaf1: String) = pattern findFirstMatchIn sPair match {
    //*case Some(m) => m group "leaf"//*works
    /*if no cast -- returns pair of type 'Any'*/
    case Some(m) => ((m group "node").toInt, (m group "leaf").toString()) //*works
    //case None => "No copyright"
    case _ => "exception"
  };System.out.println("""node1  : Int = """ + $show(node1 ));System.out.println("""leaf1  : String = """ + $show(leaf1 ));$skip(76); val res$38 =  //*works

  for (m <- pattern findFirstMatchIn sPair) yield m group "node";System.out.println("""res38: Option[String] = """ + $show(res$38));$skip(74); val res$39 =  //*works
  for (m <- pattern findFirstMatchIn sPair) yield m group "leaf";System.out.println("""res39: Option[String] = """ + $show(res$39));$skip(92); val res$40 =  //*works
  for (m <- pattern findFirstMatchIn sPair) yield (m group "node", m group "leaf");System.out.println("""res40: Option[(String, String)] = """ + $show(res$40));$skip(122); val res$41 =  //*works

  for {
    p <- graph2
    leaf <- 1 to 7
    node <- 1 until 7
    if (node + " " + leaf == p)
  } yield (node, leaf);System.out.println("""res41: Array[(Int, Int)] = """ + $show(res$41));$skip(122); 
  //*val Array(xi, yi) = for (i <- graph2(0) split " ") yield i.toInt
  val n = 7;System.out.println("""n  : Int = """ + $show(n ));$skip(82);  // le nombre 'n' de relations au total.
  //var inputStore: Array[(T1, T2)]
  var inputStore: Array[(Int, Int)] = Array();System.out.println("""inputStore  : Array[(Int, Int)] = """ + $show(inputStore ));$skip(49); 
  var inputVector: Vector[(Int, Int)] = Vector();System.out.println("""inputVector  : Vector[(Int, Int)] = """ + $show(inputVector ));$skip(687); 
  //inputStore ++ Array(1, 2)
  for (i <- 0 until n) {
    /*pattern matching*/
    val Array(xi, yi) = for (i <- graph2(i) split " ") yield i.toInt
    //inputStore +: Array((xi, yi))
    //inputStore :+ Array((xi, yi))
    //inputStore :++ Array((xi, yi))
    //inputStore ++: Array((xi, yi))
    //inputStore ++ Array((xi, yi))
    //inputStore ++ Array(xi, yi)
    /*vector of pairs*/
    inputVector = inputVector ++ Vector((xi, yi))
    /*array of pairs*/
    inputStore = inputStore :+ (xi, yi)
    //inputStore union Array(xi, yi)
    //inputStore :+ Array(xi, yi)
    println("(" + xi + "," + yi +
      "),size:" + inputStore.size +
      ",isEmpty:" + inputStore.isEmpty)
  };$skip(13); val res$42 = 
  inputStore;System.out.println("""res42: Array[(Int, Int)] = """ + $show(res$42));$skip(14); val res$43 = 
  inputVector;System.out.println("""res43: Vector[(Int, Int)] = """ + $show(res$43));$skip(18); val res$44 = 
  inputStore.size;System.out.println("""res44: Int = """ + $show(res$44));$skip(37); val res$45 = 
  inputStore.mkString("[", ",", "]");System.out.println("""res45: String = """ + $show(res$45));$skip(41); 
  val inputVector2 = inputStore.toVector;System.out.println("""inputVector2  : Vector[(Int, Int)] = """ + $show(inputVector2 ));$skip(20); val res$46 = 
  inputVector2.head;System.out.println("""res46: (Int, Int) = """ + $show(res$46));$skip(18); val res$47 = 
  inputVector2(2);System.out.println("""res47: (Int, Int) = """ + $show(res$47));$skip(20); val res$48 = 
  inputVector2.last;System.out.println("""res48: (Int, Int) = """ + $show(res$48));$skip(104); val res$49 = 
  for {
    i <- 0 until n
    //Array(node, leaf) <- graph2(i) split " "
  } yield graph2(i) split " ";System.out.println("""res49: scala.collection.immutable.IndexedSeq[Array[String]] = """ + $show(res$49));$skip(77); 

  //Array("1 2", "2 3")(0).split(" ")
  val gr2Elem0 = graph2(0) split " ";System.out.println("""gr2Elem0  : Array[String] = """ + $show(gr2Elem0 ));$skip(48); 
  val Array(node2, leaf2) = graph2(0) split " ";System.out.println("""node2  : String = """ + $show(node2 ));System.out.println("""leaf2  : String = """ + $show(leaf2 ));$skip(14); val res$50 = 
  gr2Elem0(0);System.out.println("""res50: String = """ + $show(res$50));$skip(14); val res$51 = 
  gr2Elem0(1);System.out.println("""res51: String = """ + $show(res$51));$skip(53); val res$52 = 
  //*representation
  graph2.mkString("[", ",", "]");System.out.println("""res52: String = """ + $show(res$52));$skip(52); val res$53 = 
  graph2.exists { x => x.split(" ")(0).toInt == 4 };System.out.println("""res53: Boolean = """ + $show(res$53));$skip(41); val res$54 = 
  for (i <- graph2(0) split " ") yield i;System.out.println("""res54: Array[String] = """ + $show(res$54));$skip(47); val res$55 = 
  for (i <- graph2(0) split " ") yield i.toInt;System.out.println("""res55: Array[Int] = """ + $show(res$55));$skip(34); 
  val sampleAr1 = Array("1", "2");System.out.println("""sampleAr1  : Array[String] = """ + $show(sampleAr1 ));$skip(711); 
  //(Array("1", "2")).toInt
  //sampleAr1.toInt
  /*fork is
   * 1st consequently repeated element (as they sorted)
   * if non found then root or 1st element in array*/
  def firstFork(
    graph: Vector[(Int, Int)]): Int = {
    //graph.foreach { x => print(x + ',') }
    /*condition check*/
    //graph.forall { x => ??? }
	    val fork = (for {
	      p <- graph
	      if /*{
	        val currNode = p._1
	        val dub1 = graph2.filter { x => x.split(" ")(0).toInt == currNode }
	        !(dub1.isEmpty)
	      } || {*/ (graph.count { x => x._1 == p._1 }) > 1 //}
	    } yield p._1)
	    
	    if (fork.size > 0) { fork.head } else {
	      if (graph.size > 0) { graph.head._1 } else { -1 }
	    }
  };System.out.println("""firstFork: (graph: Vector[(Int, Int)])Int""");$skip(665); 
  
  def pathToRoot(
    graph: Vector[(Int, Int)],
    fromLeaf: Int,
    storedPath: Vector[(Int, Int)] = Vector()): Vector[(Int, Int)] = {
    
    if (graph.size > 0) {
      /*checking what ?*/
      if (fromLeaf == graph.head._1) {
        storedPath
      } else {
        val leafConnect = graph.find { x => x._2 == fromLeaf }
        
        if (leafConnect.size > 0) {
          //val Option((node, leaf)) = leafConnect
          pathToRoot(graph, leafConnect.get._1, storedPath ++ leafConnect)
        } else {
          storedPath //++ graph.find { x => x._2 == fromLeaf }
        }
      }
    } else {
      storedPath
    }
  };System.out.println("""pathToRoot: (graph: Vector[(Int, Int)], fromLeaf: Int, storedPath: Vector[(Int, Int)])Vector[(Int, Int)]""");$skip(189);  //*work as expected

  def treeLeafs(graph: Vector[(Int, Int)]): Vector[Int] = {
    for {
      elem <- graph
      if (graph.count { x => x._1 == elem._2 } == 0)
    } yield elem._2
  };System.out.println("""treeLeafs: (graph: Vector[(Int, Int)])Vector[Int]""");$skip(557);  //*work as expected

  def longestPath(
    graph: Vector[(Int, Int)],
    leafs: Vector[Int]): Vector[(Int, Int)] = {
    
	    var maxPath: Vector[(Int, Int)] = Vector() //graph//.size
	    var currentPath: Vector[(Int, Int)] = Vector()
	    /*must be collection of paths from leaf to root
		      then pick longese / biggest / max length */
	    leafs.foreach { elem =>
	      currentPath = pathToRoot(graph, elem)
	      if (currentPath.size > maxPath.size) {
	        maxPath = currentPath
	      } else {
	        // maxPath
	      }
	    }
	
	    maxPath
  };System.out.println("""longestPath: (graph: Vector[(Int, Int)], leafs: Vector[Int])Vector[(Int, Int)]""");$skip(1549);  //*works

  def pathToFork(
    graph: Vector[(Int, Int)],
    leafs: Vector[Int],
    fork: Int,
    maxPath: Vector[(Int, Int)]): Vector[(Int, Int)] = {

    var minPath: Vector[(Int, Int)] = Vector() //graph//.size
    var currentPath: Vector[(Int, Int)] = Vector()
    /*for final check*/
    val fromForkToRoot = pathToRoot(graph, fork)

    def upwardPath(
      fromLeaf: Int,
      //to: Int,
      storedPath: Vector[(Int, Int)] = Vector()): Vector[(Int, Int)] = {

      if (fromLeaf == fork) {
        storedPath
      } else {
        val leafConnect = graph.find { x => x._2 == fromLeaf }
        if (leafConnect.size > 0) {
          upwardPath(leafConnect.get._1, storedPath ++ leafConnect)
        } else {
          storedPath //++ graph.find { x => x._2 == fromLeaf }
        }
      }
    }
    /*must be max path in test
    bun less then over all maxPath*/
    /*if path > the maxPath skip element & check next */
    //println("leafs.size:" + leafs.size)
    for (i <- 0 until leafs.size) {
      currentPath = upwardPath(leafs(i))
      //println("currentPath:" + currentPath +
        //", for leafs(i):" + leafs(i))
      if (currentPath.size >= maxPath.size) {
        //skip elem ?
          minPath = maxPath
      } else {
        if (currentPath.size > minPath.size) {
          //println("minPath was:" + minPath)
          minPath = currentPath
        } else {
          // minPath
        }
      }
    }

    if (fromForkToRoot.size > minPath.size) {
      fromForkToRoot
    } else {
      minPath
    }
  };System.out.println("""pathToFork: (graph: Vector[(Int, Int)], leafs: Vector[Int], fork: Int, maxPath: Vector[(Int, Int)])Vector[(Int, Int)]""");$skip(85);  //*works

  val lineVecTest = Vector((1, 2), (2, 3), (3, 4), (4, 5), (5, 6), (6, 7), (7, 8));System.out.println("""lineVecTest  : scala.collection.immutable.Vector[(Int, Int)] = """ + $show(lineVecTest ));$skip(74); 
  val test1 = Vector((0, 1),
    (1, 2),
    (2, 3),
    (2, 4));System.out.println("""test1  : scala.collection.immutable.Vector[(Int, Int)] = """ + $show(test1 ));$skip(98);  //min==2
  val test2 = Vector((0, 1),
    (1, 2),
    (1, 4),
    (2, 3),
    (4, 5),
    (4, 6));System.out.println("""test2  : scala.collection.immutable.Vector[(Int, Int)] = """ + $show(test2 ));$skip(298);  //min==2
  val test3 = Vector((0, 1),
    (0, 8),
    (0, 15),
    (1, 2),
    (1, 5),
    (2, 3),
    (2, 4),
    (5, 6),
    (5, 7),
    (8, 9),
    (8, 12),
    (9, 10),
    (9, 11),
    (12, 13),
    (12, 14),
    (15, 16),
    (15, 19),
    (16, 17),
    (16, 18),
    (19, 20),
    (19, 21));System.out.println("""test3  : scala.collection.immutable.Vector[(Int, Int)] = """ + $show(test3 ));$skip(195);  //min==3
  /*
  test 6: fails
  on test 8:
  Process has timed out.
  This may mean that
  your solution is
  not optimized enough to handle some cases.
  vector size 30 000+
  */
  val testGraph = test3;System.out.println("""testGraph  : scala.collection.immutable.Vector[(Int, Int)] = """ + $show(testGraph ));$skip(34); 
  val fork = firstFork(testGraph);System.out.println("""fork  : Int = """ + $show(fork ));$skip(35); 
  val leafs = treeLeafs(testGraph);System.out.println("""leafs  : Vector[Int] = """ + $show(leafs ));$skip(46); 
  val maxPath = longestPath(testGraph, leafs);System.out.println("""maxPath  : Vector[(Int, Int)] = """ + $show(maxPath ));$skip(60); 
  val minPath = pathToFork(testGraph, leafs, fork, maxPath);System.out.println("""minPath  : Vector[(Int, Int)] = """ + $show(minPath ));$skip(18); val res$56 = 

  Vector() :+ 1;System.out.println("""res56: scala.collection.immutable.Vector[Int] = """ + $show(res$56));$skip(15); val res$57 = 
  inputVector2;System.out.println("""res57: Vector[(Int, Int)] = """ + $show(res$57));$skip(40); val res$58 = 
  inputVector2.count { x => x._1 == 1 };System.out.println("""res58: Int = """ + $show(res$58));$skip(40); val res$59 = 
  inputVector2.count { x => x._1 == 3 };System.out.println("""res59: Int = """ + $show(res$59));$skip(40); val res$60 = 
  inputVector2.count { x => x._1 == 8 };System.out.println("""res60: Int = """ + $show(res$60));$skip(40); val res$61 = 
  inputVector2.count { x => x._1 == 5 };System.out.println("""res61: Int = """ + $show(res$61));$skip(40); val res$62 = 
  inputVector2.count { x => x._1 == 6 };System.out.println("""res62: Int = """ + $show(res$62));$skip(40); val res$63 = 
  inputVector2.count { x => x._1 == 7 };System.out.println("""res63: Int = """ + $show(res$63));$skip(25); val res$64 = 
  firstFork(lineVecTest);System.out.println("""res64: Int = """ + $show(res$64));$skip(30); val res$65 = 
  pathToRoot(inputVector2, 1);System.out.println("""res65: Vector[(Int, Int)] = """ + $show(res$65));$skip(30); val res$66 = 
  pathToRoot(inputVector2, 3);System.out.println("""res66: Vector[(Int, Int)] = """ + $show(res$66));$skip(30); val res$67 = 
  pathToRoot(inputVector2, 8);System.out.println("""res67: Vector[(Int, Int)] = """ + $show(res$67))}
}
