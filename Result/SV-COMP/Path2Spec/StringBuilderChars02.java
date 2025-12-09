public class StringBuilderChars02 {
  //@ requires arg != null;
  public static boolean f(String arg) {
    StringBuilder buffer = new StringBuilder(arg);
    return buffer.toString().equals("DiffBlue Limitted");
  }
}