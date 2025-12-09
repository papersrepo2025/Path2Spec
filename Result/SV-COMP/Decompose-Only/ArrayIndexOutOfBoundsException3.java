public class ArrayIndexOutOfBoundsException3 {

  /*@
    @ public normal_behavior
    @   requires 0 <= index && index < 4;
    @   assignable \nothing;
    @   ensures \result == true;
    @ also
    @ public normal_behavior
    @   requires index < 0 || 4 <= index;
    @   assignable \nothing;
    @   ensures \result == false;
    @*/
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