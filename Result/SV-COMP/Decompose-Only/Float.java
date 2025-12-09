public class Float {

  //@ requires (a + b != a) || (b == 0.0);
  //@ ensures \result;
  //@ also
  //@ requires !((a + b != a) || (b == 0.0));
  //@ ensures !\result;
  public static boolean f(float a, float b) {
    return (a + b != a || b == 0.0);
  }
}