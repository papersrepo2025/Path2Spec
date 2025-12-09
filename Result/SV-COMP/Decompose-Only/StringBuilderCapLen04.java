public class StringBuilderCapLen04 {
  //@ requires arg != null;
  //@ assignable \nothing;
  //@ ensures true;
  public static boolean f(String arg) {
    StringBuilder buffer = new StringBuilder(arg);
    return buffer.capacity() == 69;
  }
}