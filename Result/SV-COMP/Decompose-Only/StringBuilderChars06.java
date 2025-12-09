public class StringBuilderChars06 {
  /*@
    @ requires arg != null;
    @ ensures \result == true || \result == false;
    @ also
    @ requires arg == null;
    @ signals (NullPointerException e) true;
    @*/
  public static boolean f(String arg) {
    StringBuilder buffer = new StringBuilder(arg);
    buffer.reverse();
    return buffer.toString().equals("detimiL eTlBffiiH");
  }
}