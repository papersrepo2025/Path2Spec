class Main {

  //@ ensures (x >= 0.0f && x <= Long.MAX_VALUE / 2) ==> (\result <==> ((long)(x + 1.0f) > 0L));
  //@ ensures !(x >= 0.0f && x <= Long.MAX_VALUE / 2) ==> \result == true;
  public static boolean f(float x) {
    if (x >= 0.0f && x <= Long.MAX_VALUE / 2) {
      Main inst = new Main();
      return inst.test(x);
    }
    return true;
  }

  //@ ensures \result <==> ((long)(\old(x) + 1.0f) > 0L);
  public boolean test(float x) {

    long res = (long) ++x;
    if (res > 0) System.out.println("x >0");
    else {
      return false;
    }
    return true;
  }
}