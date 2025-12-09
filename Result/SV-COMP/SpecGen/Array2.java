public class Array2 {

  //@ ensures \result;
  public static boolean func(int unknown) {
    int[] arr;
    if (unknown > 0)
      arr = new int[1];
    else
      arr = new int[0];

    if (unknown > 0)
      arr[0] = 1;

    if (unknown > 0)
      return arr.length == 1 && arr[0] == 1;
    else
      return arr.length == 0;
  }

}