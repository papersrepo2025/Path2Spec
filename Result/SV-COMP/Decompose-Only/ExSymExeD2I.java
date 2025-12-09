class Main {

  //@ requires 0.0 <= x && x <= Integer.MAX_VALUE / 2; 
  //@ ensures \result;
  //@ also
  //@ requires !(0.0 <= x && x <= Integer.MAX_VALUE / 2); 
  //@ ensures \result;
  public static boolean f (double x) {
    if (x >= 0.0 && x <= Integer.MAX_VALUE / 2) {
      Main inst = new Main();
      return inst.test(x);
    }
    return true;
  }

  //@ requires 0.0 <= x && x <= Integer.MAX_VALUE / 2;
  //@ ensures \result;
  //@ also
  //@ requires ((int)(x + 1)) > 0;
  //@ ensures \result;
  //@ also
  //@ requires !(((int)(x + 1)) > 0);
  //@ ensures !\result;
  //@ also
  //@ ensures \result == (((int)(\old(x) + 1)) > 0);
  public boolean test(double x) {

    int res = (int) ++x;
    if (res > 0) System.out.println("x >0");
    else {
      return false;
    }
    return true;
  }
}