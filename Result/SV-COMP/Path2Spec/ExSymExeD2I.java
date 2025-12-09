class Main {
  //@ ensures (x < 0.0 || x > (Integer.MAX_VALUE / 2) || x != x) ==> \result;
  //@ also
  //@ requires true;
  //@ ensures x >= 0.0 ==> \result;
  //@ also
  //@ requires 0.0 <= x && x <= Integer.MAX_VALUE / 2 && java.lang.System.out != null;
  //@ ensures \result == true;
  public static boolean f (double x) {
    if (x >= 0.0 && x <= Integer.MAX_VALUE / 2) {
      Main inst = new Main();
      return inst.test(x);
    }
    return true;
  }
  //@ ensures true;
  //@ also
  //@ requires x >= 0.0;
  //@ ensures \result;
  //@ also
  //@ requires 0.0 <= x && x <= Integer.MAX_VALUE / 2 && java.lang.System.out != null;
  //@ requires Integer.MIN_VALUE <= x + 1.0 && x + 1.0 <= Integer.MAX_VALUE;
  //@ ensures \result == true;
  public boolean test(double x) {

    int res = (int) ++x;
    if (res > 0) System.out.println("x >0");
    else {
      return false;
    }
    return true;
  }
}
