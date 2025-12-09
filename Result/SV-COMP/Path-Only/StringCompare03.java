public class StringCompare03 {
  //@ requires s3 != null && s4 != null && s3.regionMatches(true, 0, s4, 0, 5);
  //@ ensures \result == false;
  //@ also
  //@ requires s3 != null && s4 != null;
  //@ requires s3.regionMatches(true, 0, s4, 0, 5);
  //@ ensures \result == false;
  //@ also
  //@ requires s3 != null && s4 != null && !s3.regionMatches(true, 0, s4, 0, 5);
  //@ ensures \result == true;
  //@ also
  //@ requires s3 != null && s4 != null;
  //@ ensures \result == !s3.regionMatches(true, 0, s4, 0, 5);
  public static boolean f(String s3, String s4) {
    // test regionMatches (ignore case)
    return !s3.regionMatches(true, 0, s4, 0, 5);
  }
}
