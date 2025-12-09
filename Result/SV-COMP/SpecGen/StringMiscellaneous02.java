public class StringMiscellaneous02 {
  /*@ public normal_behavior
    @   requires s1 != null;
    @   ensures \result <==> (s1.length() == 24);
    @   assignable \nothing;
    @*/
  public static boolean f(String s1) {
    return s1.length() == 24;
  }
}