public class shifting2 {
  //@ requires i < 1 || i > 100;
  //@ ensures \result == -1;
  //@ also
  //@ requires i < 1 || i > 100 || (0 <= i && i < 32);
  //@ ensures (i < 1 || i > 100) ==> \result == -1;
  //@ also
  //@ requires 1 <= i && i <= 100 && (i % 32) != 0;
  //@ requires 0 <= i && i < 32;
  //@ ensures \result == 1;
  //@ also
  //@ requires 1 <= i && i <= 31 && (i % 32) != 0;
  //@ ensures \result == 1;
  //@ also
  //@ requires 1 <= i && i <= 31 && (i % 32) == 0;
  //@ ensures \result == 0;
  //@ also
  //@ requires 0 <= i && i < 32;
  //@ ensures (i < 1 || i > 100) ==> \result == -1;
  //@ ensures (1 <= i && i <= 100 && (i % 32) == 0) ==> \result == 0;
  public static int f(int i) {

    if (i < 1 || i > 100) {
      return -1;
    }

    return ((1 << i) != 1) ? 1 : 0;
  }
}
