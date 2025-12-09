public class shifting3 {

  //@ public normal_behavior;
//@ requires i < 1 || i > 100;
//@ ensures \result == -1;
  public static int f(int i) {
    if (i < 1 || i > 100) {
      return -1;
    }

    return ((1L << i) != 1L) ? 1 : 0;
  }
}