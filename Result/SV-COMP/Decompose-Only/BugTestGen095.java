public class BugTestGen095 {
  //@ public normal_behavior
  //@   requires arg != null;
  //@   assignable \nothing;
  //@   ensures true;
  //@ also
  //@ public exceptional_behavior
  //@   requires arg == null;
  //@   assignable \nothing;
  //@   signals_only NullPointerException;
  public static boolean fun(String arg) {
    StringBuilder sb = new StringBuilder(arg);
    sb.append("Z");
    String s = sb.toString();
    return (s.equals("fg"));
  }
}