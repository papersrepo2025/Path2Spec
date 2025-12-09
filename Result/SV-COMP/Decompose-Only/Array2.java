public class Array2 {

  //@ requires unknown > 0;
  //@ ensures \result;
  //@ also
  //@ requires unknown <= 0;
  //@ ensures \result;
  //@ also
  //@ ensures \result;
  public static boolean func(int unknown) {
    int[] arr;
    if (unknown > 0)
      //@ assert unknown > 0;
      arr = new int[1];
    else
      //@ assert unknown <= 0;
      arr = new int[0];

    if (unknown > 0)
      //@ assert arr != null && arr.length == 1;
      arr[0] = 1;

    if (unknown > 0)
      //@ assert arr.length == 1 && arr[0] == 1;
      return arr.length == 1 && arr[0] == 1;
    else
      //@ assert arr.length == 0;
      return arr.length == 0;
  }

}