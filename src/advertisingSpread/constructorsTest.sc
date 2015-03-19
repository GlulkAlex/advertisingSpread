package advertisingSpread

object constructorsTest {
  class AboutConstructorWithAuxiliaryConstructor( val name: String ) {
    // invoke auxiliary constructor
    /*must be method body*/
    def this() /*= this*//*()*//*???*/ {
      // what happens if you comment out the following line?
      /*must be something with 'this(param)'*/
      this( "defaultname" )
    }
  }

  val aboutMe = new AboutConstructorWithAuxiliaryConstructor()
                                                  //> aboutMe  : advertisingSpread.constructorsTest.AboutConstructorWithAuxiliaryC
                                                  //| onstructor = advertisingSpread.constructorsTest$AboutConstructorWithAuxiliar
                                                  //| yConstructor@14f9431
  val aboutMe2 = new AboutConstructorWithAuxiliaryConstructor("Alex")
                                                  //> aboutMe2  : advertisingSpread.constructorsTest.AboutConstructorWithAuxiliary
                                                  //| Constructor = advertisingSpread.constructorsTest$AboutConstructorWithAuxilia
                                                  //| ryConstructor@163425a
  aboutMe.name /*should be ()*/                   //> res0: String = defaultname
  aboutMe2.name /*should be ()*/                  //> res1: String = Alex
}