public class StringBuilderChars03 {
  //@ requires arg != null;
  //@ requires arg.length() >= 5;
 
  public static boolean f(String arg) {
    StringBuilder buffer = new StringBuilder(arg);
    return buffer.charAt(0) == buffer.charAt(4);
  }
}
