public class StringValueOf03 {
  /*@
    @ requires arg != null && arg.length() < 8;
    @ ensures \result == false;
    @*/
  public static boolean f(String arg) {
    if (arg.length() < 8)
      return false;
    char[] charArray = {
      arg.charAt(0), arg.charAt(1), arg.charAt(2),
      arg.charAt(3), arg.charAt(4), arg.charAt(5),
      arg.charAt(6), arg.charAt(7)
    };
    String tmp = String.valueOf(charArray, 3, 3);
    return tmp.equals("fbbl");
  }
}