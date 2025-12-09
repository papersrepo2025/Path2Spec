public class StringValueOf07 {
  /*@ public normal_behavior
    @ ensures \result <==> (longValue == 100000000000L);
    @ assignable \nothing;
    @*/
  public static boolean f(long longValue) {
    String tmp = String.valueOf(longValue);
    return tmp.equals("100000000000");
  }
}