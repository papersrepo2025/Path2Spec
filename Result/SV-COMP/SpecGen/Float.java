public class Float {

  /*@ ensures \result == \result; @*/
  public static boolean f(float a, float b) {
    return (a + b != a || b == 0.0);
  }
}