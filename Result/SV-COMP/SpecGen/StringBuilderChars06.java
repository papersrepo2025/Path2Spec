public class StringBuilderChars06 {
  /*@ public normal_behavior
    @   requires arg != null;
    @   ensures \result == arg.equals("HiiffBlTe Limited");
    @ also
    @ public exceptional_behavior
    @   requires arg == null;
    @   signals (NullPointerException e) true;
    @*/
  /*@ spec_public @*/ public static boolean f(String arg) {
    StringBuilder buffer = new StringBuilder(arg);
    buffer.reverse();
    return buffer.toString().equals("detimiL eTlBffiiH");
  }
}