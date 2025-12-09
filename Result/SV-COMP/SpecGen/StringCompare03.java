public class StringCompare03 {
  //@ requires s3 != null && s4 != null;
  //@ assignable \nothing;
  //@ ensures \result == (!s3.regionMatches(true, 0, s4, 0, 5));
  public /*@ pure @*/ static boolean f(String s3, String s4) {
    // test regionMatches (ignore case)
    return !s3.regionMatches(true, 0, s4, 0, 5);
  }
}