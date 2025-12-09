class Main {

  //@ requires x >= 0.0f && x <= Long.MAX_VALUE / 2;
  //@ ensures \result == true;
  //@ also
  //@ requires x < 0.0f || x > Long.MAX_VALUE / 2;
  //@ ensures \result == true;
  //@ also
  //@ ensures \result == true;
  public static boolean f(float x) {
    if (x >= 0.0f && x <= Long.MAX_VALUE / 2) {
      Main inst = new Main();
      return inst.test(x);
    }
    return true;
  }

  //@ requires ((long)(x + 1.0f)) > 0L;
  //@ ensures \result == true;
  //@ also
  //@ requires ((long)(x + 1.0f)) <= 0L;
  //@ ensures \result == false;
  //@ also
  //@ ensures \result <==> ((long)(\old(x) + 1.0f)) > 0L;
  public boolean test(float x) {

    //@ assert true;
    long res = (long) ++x;
    if (res > 0) System.out.println("x >0");
    else {
      return false;
    }
    return true;
  }
}