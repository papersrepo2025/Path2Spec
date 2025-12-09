public class ArithmeticException6 {

  //@ ensures \result == (denom != 0);
  //@ assignable \nothing;
  public static boolean arithmeticException6(int denom) {
    try {
      int j = 10 / denom;
    } catch (ArithmeticException exc) {
      return false;
    }
    return true;
  }

}