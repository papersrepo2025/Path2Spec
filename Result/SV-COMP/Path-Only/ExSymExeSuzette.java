class Main {
  //@ ensures \result == !(x > 10 || y > 10);
  //@ also
  //@ requires x <= 10 && y <= 10;
  //@ ensures \result == true;
  //@ also
  //@ ensures \result == (x <= 10 && y <= 10);
  public boolean test(int x, int y) {

    int v = method_a(x, y);

    if (v > 0) {
      return false;
    }
    return true;
  }
  //@ ensures (x > 10) ==> \result == x;
  //@ ensures (x <= 10 && y > 10) ==> \result == y;
  //@ ensures (x <= 10 && y <= 10) ==> \result == 0;
  //@ also
  //@ ensures x > 10 ==> \result == x;
  //@ ensures x <= 10 && y > 10 ==> \result == y;
  //@ ensures x <= 10 && y <= 10 ==> \result == 0;
  //@ also
  //@ requires x <= 10 && y <= 10;
  //@ ensures \result == 0;
  //@ also
  //@ requires true;
  //@ ensures (x > 10 ==> \result == x);
  public int method_a(int x, int y) {

    if (x > 10) return x;

    if (y > 10) return y;

    return 0;
  }
  //@ ensures \result == z;
  //@ also
  //@ ensures \result == \old(z);
  //@ also
  //@ requires z > 10 && z < Integer.MAX_VALUE;
  //@ ensures \result == \old(z);
  //@ also
  //@ requires z <= 10;
  //@ ensures \result == \old(z);
  public int method_b(int z) {

    if (z > 10) return z++;
    else return z--;
  }
  //@ ensures \result == true;
  //@ also
  //@ ensures (arg < 0 || arg > 10) ==> \result == true;
  public static boolean f(int arg) {

    Main ex = new Main();
    if (arg < 0 || arg > 10) return true;
    return ex.test(arg, 0);
  }
}
