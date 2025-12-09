public class ArithmeticException1 {

  //@ requires i != 0;
  //@ assignable \nothing;
  //@ ensures \result == true;
  //@ also
  //@ requires i == 0;
  //@ assignable \nothing;
  //@ ensures \result == false;
  public static boolean arithmeticException1(int i) {
    try {
      int j = 10 / i;
    } catch (ArithmeticException exc) {
      return false;
    }
    return true;
  }

}