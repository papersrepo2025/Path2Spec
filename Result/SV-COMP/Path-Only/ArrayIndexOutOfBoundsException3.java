public class ArrayIndexOutOfBoundsException3 {
  //@ requires 0 <= index && index < 4;
  //@ ensures \result == true;
  //@ also
  //@ requires index < 0 || index >= 4;
  //@ ensures \result == false;
  public static boolean func(int index) {
    try {
      int[] a = new int[4];
      a[index] = 0;
    } catch (Exception exc) {
      return false;
    }
    return true;
  }
}
