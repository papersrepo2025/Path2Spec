public class shifting {
  //@ requires i < 0 || i > 100;
  //@ ensures \result == -1;
  //@ also
  //@ requires i < 0 || i > 100 || (0 <= i && i < 64);
  //@ ensures \result == -1 || \result == 0 || \result == 1;
  //@ also
  //@ requires 0 <= i && i <= 63;
  //@ ensures (0 <= i && i <= 30) ==> \result == 1;
  //@ ensures (31 <= i && i <= 62) ==> \result == 0;
  //@ ensures (i == 63) ==> \result == 1;
  public static int f(int i) {
    if (i < 0 || i > 100) {
      return -1;
    }

    if ((1L << i) > Integer.MAX_VALUE) {
      return 0;
    }
    return 1;
  }
}
