public class ArithmeticException5 {
  /*@
    @ requires i != 0;
    @ assignable \nothing;
    @ ensures \result == true;
    @ also
    @ requires i == 0;
    @ assignable \nothing;
    @ ensures \result == true;
    @*/
  public static boolean arithmeticException5(double i) {
    try {
      //@ requires i != 0;
      double j = 10 / i;
    } catch (ArithmeticException exc) {
      return false;
    }
    return true;
  }
}