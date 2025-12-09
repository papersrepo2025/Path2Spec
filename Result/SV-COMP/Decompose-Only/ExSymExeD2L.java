class Main {

  /*@ public normal_behavior
    @   requires true;
    @   assignable \everything;
    @   ensures \result == true;
    @*/
  public static boolean f(double x) {
    if (x >= 0.0 && x <= Long.MAX_VALUE / 2) {
      Main inst = new Main();
      return inst.test(x);
    }
    return true;
  }

  /*@ public normal_behavior
    @   requires true;
    @   assignable \everything;
    @   ensures \result <==> ((long)(\old(x) + 1.0) > 0L);
    @*/
  public boolean test(double x) {

    long res = (long) ++x;
    if (res > 0) System.out.println("x >0");
    else {
      return false;
    }
    return true;
  }
}