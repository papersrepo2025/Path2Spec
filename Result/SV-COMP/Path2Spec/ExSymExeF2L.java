class Main {
  //@ requires 0.0f <= x && x <= (float)(Long.MAX_VALUE / 2L);
  //@ ensures \result == true;
  //@ also
  //@ ensures !(x >= 0.0f && x <= (float)(Long.MAX_VALUE / 2L)) ==> \result;
  //@ also
  //@ requires System.out != null;
  //@ ensures ((long)(x + 1.0f) > 0) ==> \result;
  //@ also
  //@ ensures (x < 0.0f || x > Long.MAX_VALUE / 2) ==> \result == true;
  //@ ensures (0.0f <= x && x <= Long.MAX_VALUE / 2 && (long)(x + 1.0f) <= 0L) ==> \result == false;
  //@ ensures (0.0f <= x && x <= Long.MAX_VALUE / 2 && (long)(x + 1.0f) > 0L) ==> \result == true;
  public static boolean f(float x) {
    if (x >= 0.0f && x <= Long.MAX_VALUE / 2) {
      Main inst = new Main();
      return inst.test(x);
    }
    return true;
  }
  //@ requires 0.0f <= x && x <= (float)(Long.MAX_VALUE / 2L);
  //@ ensures \result == true;
  //@ ensures (long)(\old(x) + 1.0f) > 0;
  //@ also
  //@ requires System.out != null;
  //@ ensures ((long)(\old(x) + 1.0f) > 0) ==> \result;
  //@ also
  //@ ensures ((long)(\old(x) + 1.0f) <= 0L) ==> \result == false;
  //@ ensures ((long)(\old(x) + 1.0f) > 0L) ==> \result == true;
  public boolean test(float x) {

    long res = (long) ++x;
    if (res > 0) System.out.println("x >0");
    else {
      return false;
    }
    return true;
  }
}
