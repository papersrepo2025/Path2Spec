public class StringBuilderCapLen03 {
  //@ requires arg != null && arg.length() == 51;
  //@ ensures \result == true;
  //@ also
  //@ requires arg != null;
  //@ requires arg.length() != 51;
  //@ ensures \result == false;
  public static boolean f(String arg) {
    StringBuilder buffer = new StringBuilder(arg);
    return buffer.length() == 51;
  }
}
