public class StringValueOf04 {
  /*@ public normal_behavior
    @   ensures \result == booleanValue;
    @   assignable \nothing;
    @*/
  public static boolean f(boolean booleanValue) {
    String tmp = String.valueOf(booleanValue);
    return tmp.equals("true");
  }
}