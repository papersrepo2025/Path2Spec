public class NegativeArraySizeException1 {
  /*@ ensures \result == false; @*/
  public static boolean f() {
    try {
      int a[] = new int[-1];
    } catch (NegativeArraySizeException exc) {
      return false;
    }
    return true;
  }
}