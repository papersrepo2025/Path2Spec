class Main {
  //@ requires true;
  //@ ensures \result == true;
  //@ also
  //@ requires 0 <= x && x <= 2147483646 && System.out != null;
  //@ ensures \result == true;
  //@ also
  //@ ensures \result == true;
  public static boolean f(int x) {
    if (x != 3 && x != 0)
      return true;

    Main inst = new Main();
    return inst.test(x);
  }
  //@ requires true;
  //@ ensures (\old(x) == 3 || \old(x) == 0) ==> \result;
  //@ also
  //@ requires x < Integer.MAX_VALUE;
  //@ requires System.out != null;
  //@ ensures \result == (\old(x) >= 0);
  //@ also
  //@ ensures \result == (\old(x) == Integer.MAX_VALUE ? false : \old(x) + 1 > 0);
  public boolean test(int x) {
    float res = (float) ++x;
    if (res > 0) System.out.println("x >0");
    else {
      return false;
    }
    return true;
  }
}
