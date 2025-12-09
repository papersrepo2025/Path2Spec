public class ArithmeticException5 {
  //@ requires i != 0.0;
  //@ ensures \result == true;
  //@ also
  //@ requires false;
  //@ requires i != 0;
  //@ ensures \result == false;
  public static boolean arithmeticException5(double i) {
    try {
      double j = 10 / i;
    } catch (ArithmeticException exc) {
      return false;
    }
    return true;
  }
}
