public class radians {

  /*@ public normal_behavior
    @ ensures \result == (java.lang.Math.toRadians(deg) >= 0);
    @ assignable \nothing;
    @*/
  /*@ pure @*/
  public static boolean f(double deg) {
    double rad = java.lang.Math.toRadians(deg);
    if (rad >= 0) {
      return true;
    } else {
      return false;
    }
  }
}