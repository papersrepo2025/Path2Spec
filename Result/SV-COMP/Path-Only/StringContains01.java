public class StringContains01 {
  //@ requires ab != null && b != null;
  //@ also
  //@ requires ab != null && b != null;
  //@ ensures \result == ab.contains(b);
  //@ also
  //@ requires ab != null && b != null && !ab.contains(b);
  //@ ensures \result == false;
  //@ also
  //@ requires ab != null && b != null;
  //@ ensures \result == ab.contains(b);
  //@ ensures !ab.contains(b) ==> \result == false;
  public static boolean f (String ab, String b) {
    return (ab.contains(b));
  }
}
