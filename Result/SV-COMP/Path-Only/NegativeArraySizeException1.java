public class NegativeArraySizeException1 {
  //@ requires true;
  //@ ensures \result == false;
  //@ also
  //@ requires false;
  //@ ensures \result == true;
  public static boolean f() {
    try {
      int a[] = new int[-1];
    } catch (NegativeArraySizeException exc) {
      return false;
    }
    return true;
  }
}
