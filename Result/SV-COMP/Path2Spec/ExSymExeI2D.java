class Main {
  //@ requires true;
  //@ ensures \result == true;
  //@ also
  //@ requires 0 <= x && x <= 2147483646 && java.lang.System.out != null;
  //@ ensures \result == true;
  //@ also
  //@ requires x <= -1 || x == 2147483647;
  //@ ensures \result == true;
  public static boolean main(int x) {
    x = x > 0 ? x % 10 : -(x % 10);

    //@ assert 0 <= x && x <= 9;
    Main inst = new Main();
    return inst.test(x);
  }
  //@ requires true;
  //@ ensures \result == (\old(x) >= 0);
  //@ also
  //@ requires x < 2147483647 && java.lang.System.out != null;
  //@ ensures \result == ((\old(x) + 1) > 0);
  //@ also
  //@ requires x >= 0 && x != Integer.MAX_VALUE;
  //@ ensures \result == true;
  public boolean test(int x) {
    double res = (double) ++x;
    if (res > 0) System.out.println("x >0");
    else {
      return false;
    }
    return true;
  }
}
