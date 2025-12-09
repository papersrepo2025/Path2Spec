public class StringCompare02 {
  //@ requires s3 != null && s4 != null && s3.length() >= 5 && s4.length() >= 5 && s3.substring(0, 5).equals(s4.substring(0, 5));
//@ ensures \result == true;
  public static boolean f(String s3, String s4) {
    // test regionMatches (case sensitive)
    return s3.regionMatches(0, s4, 0, 5);
  }
}