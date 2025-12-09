public class StringBuilderChars02 {
  /*@
    @ requires arg != null;
    @ ensures \result <==> arg.equals("DiffBlue Limitted");
    @*/
  public /*@ spec_public @*/ static boolean f(String arg) {
    StringBuilder buffer = new StringBuilder(arg);
    return buffer.toString().equals("DiffBlue Limitted");
  }
}