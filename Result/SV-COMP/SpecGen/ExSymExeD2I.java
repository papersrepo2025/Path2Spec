class Main {

  /*@ ensures \result; @*/
  public static boolean f (double x) {
    if (x >= 0.0 && x <= Integer.MAX_VALUE / 2) {
      Main inst = new Main();
      return inst.test(x);
    }
    return true;
  }

  /*@ ensures \result <==> ((int)(\old(x) + 1.0) > 0); @*/
  public boolean test(double x) {

    int res = (int) ++x;
    if (res > 0) System.out.println("x >0");
    else {
      return false;
    }
    return true;
  }
}