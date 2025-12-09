class Main {

  //@ requires x >= 0.0f && x <= Integer.MAX_VALUE / 2;
  //@ ensures \result <==> ((int)(x + 1.0f) > 0);
  //@ also
  //@ requires !(x >= 0.0f && x <= Integer.MAX_VALUE / 2);
  //@ ensures \result == true;
  public static boolean f(float x) {
    if (x >= 0.0f && x <= Integer.MAX_VALUE / 2) {
      Main inst = new Main();
      return inst.test(x);
    }
    return true;
  }

  //@ requires ((int)(x + 1.0f) > 0);
  //@ ensures \result == true;
  //@ also
  //@ requires !((int)(x + 1.0f) > 0);
  //@ ensures \result == false;
  public boolean test(float x) {

    int res = (int) ++x;
    if (res > 0) System.out.println("x >0");
    else {
      return false;
    }
    return true;
  }
}