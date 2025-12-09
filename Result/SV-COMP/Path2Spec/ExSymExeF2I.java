class Main {
  //@ requires x >= 0.0f && x <= 1073741823.0f;
  //@ requires System.out != null;
  //@ ensures \result == true;
  //@ also
  //@ requires true;
  //@ ensures (x < 0.0f || x > Integer.MAX_VALUE / 2 || x != x) ==> \result;
  //@ also
  //@ requires x >= 0.0f && !Float.isNaN(x);
  //@ requires System.out != null;
  //@ ensures \result == true;
  //@ also
  //@ requires x >= 0.0f && x == x && System.out != null;
  //@ ensures \result;
  //@ also
  //@ ensures \result == (!(x >= 0.0f && x <= Integer.MAX_VALUE / 2) || (((int)(x + 1.0f)) > 0));
  public static boolean f(float x) {
    if (x >= 0.0f && x <= Integer.MAX_VALUE / 2) {
      Main inst = new Main();
      return inst.test(x);
    }
    return true;
  }
  //@ requires System.out != null;
  //@ requires x >= 0.0f && x <= 1073741823.0f;
  //@ ensures \result == true;
  //@ also
  //@ requires true;
  //@ ensures true;
  //@ also
  //@ requires x >= 0.0f && !Float.isNaN(x);
  //@ requires System.out != null;
  //@ ensures \result == true;
  //@ also
  //@ requires x >= 0.0f && x == x && System.out != null;
  //@ ensures \result;
  //@ also
  //@ ensures \result == (((int)(\old(x) + 1.0f)) > 0);
  public boolean test(float x) {

    int res = (int) ++x;
    if (res > 0) System.out.println("x >0");
    else {
      return false;
    }
    return true;
  }
}
