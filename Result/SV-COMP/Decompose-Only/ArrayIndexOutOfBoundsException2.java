public class ArrayIndexOutOfBoundsException2 {
  /*@ 
    @ requires size < 0;
    @ ensures \result == -1;
    @ also
    @ requires 0 <= size && size < 4;
    @ ensures \result == 1;
    @ also
    @ requires size >= 4;
    @ ensures \result == 0;
    @*/
  public static int func(int size) {
    if (size < 0) return -1;
    try {
      int[] a = new int[4];
      int i = a[size];
    } catch (ArrayIndexOutOfBoundsException exc) {
      return 0;
    }
    return 1;
  }
}