public class ArithmeticException1 {

  //@ ensures \result <==> i != 0;
  public static boolean arithmeticException1(int i) {
    try {
      int j = 10 / i;
    } catch (ArithmeticException exc) {
      return false;
    }
    return true;
  }

}