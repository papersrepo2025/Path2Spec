public class BugTestGen095 {
  //@ requires arg != null;
  //@ ensures true;
  //@ also
  //@ requires arg != null;
  public static boolean fun(String arg) {
    StringBuilder sb = new StringBuilder(arg);
    sb.append("Z");
    String s = sb.toString();
    return (s.equals("fg"));
  }
}
