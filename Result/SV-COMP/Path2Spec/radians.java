public class radians {
  //@ requires deg >= 0;
  //@ requires java.lang.Math.toRadians(deg) >= 0;
  //@ ensures \result == true;
  //@ also
  //@ ensures \result == (java.lang.Math.toRadians(deg) >= 0);
  //@ also
  //@ ensures \result == (java.lang.Math.toRadians(deg) >= 0.0);
  public static boolean f(double deg) {
    double rad = java.lang.Math.toRadians(deg);
    if (rad >= 0) {
      return true;
    } else {
      return false;
    }
  }
}
