public class StringCompare04 {
  /*@ requires arg1 != null && arg2 != null;
    @ ensures \result <==> (arg2.compareTo(arg1) == 13);
    @*/
  public static boolean f(String arg1, String arg2) {
    String s1 = new String(arg1);
    String s2 = arg2;
    return s2.compareTo(s1) == 13; // false
  }
}