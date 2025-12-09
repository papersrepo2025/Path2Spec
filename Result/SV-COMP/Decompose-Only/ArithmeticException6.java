public class ArithmeticException6 {

  //@ requires denom != 0;
  //@ ensures \result == true;
  //@ also
  //@ requires denom == 0;
  //@ ensures \result == false;
  public static boolean arithmeticException6(int denom) {
    try {
      int j = 10 / denom;
    } catch (ArithmeticException exc) {
      return false;
    }
    return true;
  }

}