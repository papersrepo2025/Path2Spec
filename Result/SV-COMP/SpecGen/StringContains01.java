public class StringContains01 {
  /*@ requires ab != null && b != null;
    @ ensures \result <==> (ab.indexOf(b) >= 0);
    @*/
  public static boolean f (String ab, String b) {
    return (ab.contains(b));
  }
}