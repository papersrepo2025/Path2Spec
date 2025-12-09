class Main {
  static int field;
  static int field2;
  //@ requires arg != null;
  //@ requires System.out != null;
  //@ requires 0 <= arg && arg <= 32767;
  //@ ensures \result == true;
  //@ also
  //@ requires arg != null;
  //@ ensures \result == true;
  //@ also
  //@ requires arg != null;
  //@ also
  //@ requires arg != null && arg.shortValue() < 0;
  //@ ensures \result == true;
  public static boolean f(Short arg) {
    if (arg < 0)
      return true;
    int x = arg;

    Main inst = new Main();
    field = arg;
    return inst.test(x, arg, field2);
  }
  //@ requires System.out != null;
  //@ requires Integer.MIN_VALUE <= x + z && x + z <= Integer.MAX_VALUE;
  //@ ensures \result == (\old(x) > -3);
  //@ also
  //@ requires x == z && x >= 0;
  //@ ensures \result == true;
  //@ also
  //@ ensures (\old(x) + \old(z) <= \old(z) - 3) ==> \result == false;
  //@ ensures !(\old(x) + \old(z) <= \old(z) - 3) ==> \result == true;
  //@ also
  //@ requires x + z > z - 3;
  //@ ensures \result == true;
  //@ also
  //@ ensures \result == (\old(x) + \old(z) > \old(z) - 3);
  public boolean test(int x, int z, int r) {
    System.out.println("Testing ExSymExe14");
    int y = 3;
    r = x + z;
    x = z - y;
    z = r;
    if (z <= x) {
      System.out.println("branch FOO1");
      return false;
    } else System.out.println("branch FOO2");
    if (x <= r) System.out.println("branch BOO1");
    else System.out.println("branch BOO2");

    return true;
  }
}
