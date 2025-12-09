public class StringBuilderCapLen03 {
  /*@
    @ requires arg != null;
    @ ensures \result == (arg.length() == 51);
    @ also
    @ requires arg == null;
    @ signals (NullPointerException) true;
    @*/
  public static boolean f(String arg) {
    //@ assert arg != null;
    StringBuilder buffer = new StringBuilder(arg);
    //@ assert buffer.length() == arg.length();
    return buffer.length() == 51;
  }
}