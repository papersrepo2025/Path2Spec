public class NegativeArraySizeException1 {
  //@ requires true;
  //@ ensures \result == false;
  public static boolean f() {
    try {
      //@ assert -1 < 0;
      int a[] = new int[-1];
    } catch (NegativeArraySizeException exc) {
      return false;
    }
    return true;
  }
}