public class StringBuilderCapLen04 {
  //@ requires arg != null && arg.length() == 53;
  //@ ensures \result == true;
  //@ also
  //@ requires arg != null && arg.length() != 53;
  //@ ensures \result == false;
  public static boolean f(String arg) {
    StringBuilder buffer = new StringBuilder(arg);
    //@ assume buffer.capacity() == arg.length() + 16;
    return buffer.capacity() == 69;
  }
}
