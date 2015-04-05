package advertisingSpread

object treeObject {
  /*type A forSome {Nothing; Int}*/
  /*type A[Nothing, Int]*/
  /*type A = Nothing*/
  /*type A = [Nothing, Int]*/
  /*type A = {Nothing || Int}*/
  /*with some orbitrary 'type' from 'Nothing' to 'Any'*/
  /*? Tuple Types ?*/
  /*no instance of 'Nothing' assign '=' not work*/
  /*class Loves[ A >: Nothing <: Option[Int], B ]( val a: A = None,*/
  /*class Loves[ A <: Option[Int], B ]( val a: A = None,*/
  /*as 'A >: Nothing <: Any'*/
  class Loves[ A , B ]( val a: A = None,
                       val b: B = Option.empty ) {
    /* invoke auxiliary constructor*/
    /*? usable for emulate empty object ?*/
    /*def this() {
      /*this (Option.empty, None)*/
      /*this (Some(null), Some(None))*/
      /*this (Some[A], Some[B])*/
      /*this (-1)*/
      /*this (a, b)*/
      /*this (Option[A], Option[B])*/
      /*this (Option[A] = None, Option[B] = None)*/
      /*this (Option.empty, Option.empty)*/
      /*this (null, null)*/
      /*this (Nothing, Nothing)*/
      /*new this {type A = Int -1, type B = Boolean false}*/
      /*new Loves {type A = Int -1, type B = Boolean false}*/
      /*new Loves [type A = Int, type B = Boolean]( -1,  false)*/
      /*new this [type A = Int, type B = Boolean]( -1,  false)*/
      /*this{
        type A = Int
        type B = Boolean
        val a: A = -1
        val b: B = false
      }*/
    }*/

    override def toString = "A:" + a +
      " & B:" + b
  }

  val l1 = new Loves( Some( 1 ), Option( 2 ) )    //> l1  : advertisingSpread.treeObject.Loves[Some[Int],Option[Int]] = A:Some(1)
                                                  //|  & B:Some(2)
  val l2 = new Loves( 2, 3 )                      //> l2  : advertisingSpread.treeObject.Loves[Int,Int] = A:2 & B:3
  val l3 = new Loves( 3, Option.empty )           //> l3  : advertisingSpread.treeObject.Loves[Int,Option[Nothing]] = A:3 & B:Non
                                                  //| e
  val l4 = new Loves( Option.empty, List.empty )  //> l4  : advertisingSpread.treeObject.Loves[Option[Nothing],List[Nothing]] = A
                                                  //| :None & B:List()
  val l5 = new Loves                              //> l5  : advertisingSpread.treeObject.Loves[None.type,Option[Nothing]] = A:Non
                                                  //| e & B:None

  abstract class Tree {
  }
  /*?morph forms of SuperClass?*/
  case class Node( value: Int ) extends Tree
  case class Height( node: Node, parent: Tree ) extends Tree
  /*so may be
  Int,
  (Int & Tree includet itself)*/
  case class Forest( trees: List[ Tree ] ) extends Tree
  case class SubTree( node: Height, trees: Forest ) extends Tree
  /*object Root/*( parent: None, node: Node, trees: Forest)*/ extends SubTree {
  
  }*/

  Node( 1 )                                       //> res0: advertisingSpread.treeObject.Node = Node(1)
  Height( Node( 1 ), Node( 0 ) )                  //> res1: advertisingSpread.treeObject.Height = Height(Node(1),Node(0))
  Forest( List( Node( 2 ) ) )                     //> res2: advertisingSpread.treeObject.Forest = Forest(List(Node(2)))
  Forest( List( SubTree( Height( Node( 1 ), Node( 0 ) ),
    Forest( List( Node( 3 ), Node( 4 ) ) ) ) ) )  //> res3: advertisingSpread.treeObject.Forest = Forest(List(SubTree(Height(Node
                                                  //| (1),Node(0)),Forest(List(Node(3), Node(4))))))
  Forest( List() )                                //> res4: advertisingSpread.treeObject.Forest = Forest(List())
  Forest( List.empty )                            //> res5: advertisingSpread.treeObject.Forest = Forest(List())
  Forest( Nil )                                   //> res6: advertisingSpread.treeObject.Forest = Forest(List())
  /*trying to create JS like object*/
  /*every node in tree is
  its own separate object or
  only additional data in one big object?*/

  /*where object preserve its state or
  store its inner data?*/
  /*do not know how to use Scala object*/
  /*if it is just a set of function that return values
  depending on input parameters
  where will be that result stored?
  some predefined object like Array or Map or
  infinite Stream ?*/
  case class treeOfInt() {
    /*must be unique value
    like 'key' in 'Map'*/
    /*firstly & one option exist
    add node as a child of by default existing root*/
    def addNode( nodeVal: Int ) = ???
    /*? no 'copy' method available ?*/
    /*java.lang.CloneNotSupportedException*/
    /*java.lang.Object.clone(Native Method)*/
    /*this.clone()*/ /*Java method*/ //copy()
    /*must be only distinct values as children*/
    def addChild( nodeChild: Int ) = ???
    /*?Why?*/
    def removeChild( nodeChild: Int ) = ???
    /*? how ?
    must be performed once*/
    def addParent( parentVal: Int ) = ???
    /*initial*/
    def addHeight() = ???
    /*every time when parent in branch changes
    so
    fron node where parent changes
    dow to all descend nodes / children*/
    def resetHeight() = ???
    /*fields / properties*/
    /*'val' means defined and evaluated once & unmutable*/
    /*what is empty default values?*/
    val nodeValue: Int = -1
    /*?recursive structure?*/
    val nodeChildren: List[ Int ] = Nil
    /*?recursive structure?*/
    val nodeParent: Int = -1
    val nodeHeight: Int = -1

    /*obviously must return string*/
    override def toString = {
      "{" + nodeParent + ">>" +
        "[" + nodeValue + "]" +
        "(" + nodeChildren.mkString( "-" ) + ")}"
    }
  }

  /*without 'new' keyword return just 'type'*/
  val treeRoot = new treeOfInt                    //> treeRoot  : advertisingSpread.treeObject.treeOfInt = {-1>>[-1]()}
  /*treeRoot.addNode(2)*/
}