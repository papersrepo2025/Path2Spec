public class StringBuilderCapLen03 {
  //@ requires arg != null;
  //@ assignable \nothing;
  //@ ensures \result <==> arg.length() == 51;
  public static boolean f(String arg) {
    StringBuilder buffer = new StringBuilder(arg);
    return buffer.length() == 51;
  }
}