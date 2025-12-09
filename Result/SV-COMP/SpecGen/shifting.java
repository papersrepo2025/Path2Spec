public class shifting {

  //@ requires i < 0 || i > 100 || (0 <= i && i < 64);
  //@ ensures (i < 0 || i > 100) ==> \result == -1;
  //@ ensures (0 <= i && i <= 30) ==> \result == 1;
 
  public static /*@ pure @*/ int f(int i) {
    if (i < 0 || i > 100) {
      return -1;
    }

    //@ assert 0 <= i && i < 64;
    if ((1L << i) > Integer.MAX_VALUE) {
      return 0;
    }
    return 1;
  }
}
