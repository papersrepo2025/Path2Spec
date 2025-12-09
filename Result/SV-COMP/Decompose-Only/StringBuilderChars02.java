public class StringBuilderChars02 {
  //@ requires arg != null;
  //@ ensures true;
  //@ also
  //@ requires arg == null;
  //@ signals (NullPointerException e) true;
  public static boolean f(String arg) {
    StringBuilder buffer = new StringBuilder(arg);
    return buffer.toString().equals("DiffBlue Limitted");
  }
}