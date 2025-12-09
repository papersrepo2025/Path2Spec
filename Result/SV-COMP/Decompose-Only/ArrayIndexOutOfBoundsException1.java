public class ArrayIndexOutOfBoundsException1 {

  //@ requires size < 0;
  //@ ensures \result == -1;
  //@ also
  //@ requires 0 <= size && size < 4;
  //@ ensures \result == 1;
  //@ also
  //@ requires size >= 4;
  //@ ensures \result == 0;
  public static int func(int size) {
    if (size < 0)
      return -1;
    try {
      int[] a = new int[4];
      //@ assert a.length == 4;
      //@ assert 0 <= size;
      a[size] = 0;
    } catch (ArrayIndexOutOfBoundsException exc) {
      return 0;
    }
    return 1;
  }
  
}