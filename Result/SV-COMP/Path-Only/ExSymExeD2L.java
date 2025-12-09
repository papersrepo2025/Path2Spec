class Main {
  //@ ensures \result == true;
  //@ also
  //@ requires x >= 0.0;
  //@ requires System.out != null;
  //@ ensures \result == true;
  //@ also
  //@ ensures (x < 0.0 || x != x) ==> \result == true;
  public static boolean f(double x) {
    if (x >= 0.0 && x <= Long.MAX_VALUE / 2) {
      Main inst = new Main();
      return inst.test(x);
    }
    return true;
  }
  //@ requires x >= 0.0;
  //@ ensures \result == true;
  //@ also
  //@ requires x >= 0.0;
  //@ requires System.out != null;
  //@ ensures \result == true;
  public boolean test(double x) {

    long res = (long) ++x;
    if (res > 0) System.out.println("x >0");
    else {
      return false;
    }
    return true;
  }
}
