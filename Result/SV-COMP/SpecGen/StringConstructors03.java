public class StringConstructors03 {
  /*@ public normal_behavior
    @   requires arg0 != null;
    @   assignable \nothing;
    @   ensures \result <==> arg0.equals(arg1);
    @ also
    @ public exceptional_behavior
    @   requires arg0 == null;
    @   signals_only NullPointerException;
    @*/
  public static boolean f(String arg0, String arg1) {
    String s = new String(arg0);
    String s2 = new String(s);
    return s2.equals(arg1);
  }
}