public class StringValueOf07 {
  //@ requires longValue == 100000000000L;
  //@ ensures \result == true;
  public static boolean f(long longValue) {
    String tmp = String.valueOf(longValue);
    //@ assume tmp.equals("100000000000");
    return tmp.equals("100000000000");
  }
}
