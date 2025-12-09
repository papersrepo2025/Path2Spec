class StringContains02 {
  /*@ requires arg != null;
 
    @*/
  /*@ pure @*/ public static boolean f(String arg) {
    return (arg.contains("Hello"));
  }
}
