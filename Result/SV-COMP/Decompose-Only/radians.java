public class radians {

  /*@ public normal_behavior
    @   assignable \nothing;
    @   ensures \result <==> java.lang.Math.toRadians(deg) >= 0.0;
    @*/
  public static boolean f(double deg) {
    double rad = java.lang.Math.toRadians(deg);
    if (rad >= 0) {
      return true;
    } else {
      return false;
    }
  }
}