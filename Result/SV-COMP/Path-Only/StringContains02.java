class StringContains02 {
  //@ requires arg != null && arg.contains("Hello");
  //@ ensures \result == true;
  //@ also
  //@ requires arg != null;
  //@ ensures \result == arg.contains("Hello");
  public static boolean f(String arg) {
    return (arg.contains("Hello"));
  }
}
