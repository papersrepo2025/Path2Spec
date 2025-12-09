class StringContains02 {
  //@ requires arg != null;
  //@ ensures \result <==> arg.contains("Hello");
  public static boolean f(String arg) {
    return (arg.contains("Hello"));
  }
}