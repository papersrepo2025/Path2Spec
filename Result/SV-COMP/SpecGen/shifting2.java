public class shifting2 {

  //@ requires (i < 1 || i > 100) || (0 <= i && i < 32);
  //@ ensures (i < 1 || i > 100) ==> \result == -1;
  //@ ensures (1 <= i && i <= 100 && (i % 32) == 0) ==> \result == 0;
  //@ ensures (1 <= i && i <= 100 && (i % 32) != 0) ==> \result == 1;
  //@ ensures \result == -1 || \result == 0 || \result == 1;
  public static int f(int i) {

    if (i < 1 || i > 100) {
      return -1;
    }

    return ((1 << i) != 1) ? 1 : 0;
  }
}