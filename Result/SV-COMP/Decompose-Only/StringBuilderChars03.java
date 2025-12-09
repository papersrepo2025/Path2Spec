public class StringBuilderChars03 {
  //@ requires arg != null && arg.length() >= 5;
  //@ ensures true;
  //@ also
  //@ requires arg == null;
  //@ signals (NullPointerException e) true;
  //@ also
  //@ requires arg != null && arg.length() < 5;
  //@ signals (IndexOutOfBoundsException e) true;
  public static boolean f(String arg) {
    StringBuilder buffer = new StringBuilder(arg);
    return buffer.charAt(0) == buffer.charAt(4);
  }
}