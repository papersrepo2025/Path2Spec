public class StringBuilderCapLen04 {
  //@ requires arg != null;
  //@ ensures \result <==> (arg.length() + 16 == 69);
  public static boolean f(String arg) {
    StringBuilder buffer = new StringBuilder(arg);
    return buffer.capacity() == 69;
  }
}