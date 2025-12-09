public class BugTestGen095 {
  /*@
    @ requires arg != null;
    @ ensures \result == false;
    @*/
  public static boolean fun(String arg) {
    StringBuilder sb = new StringBuilder(arg);
    sb.append("Z");
    String s = sb.toString();
    return (s.equals("fg"));
  }
}