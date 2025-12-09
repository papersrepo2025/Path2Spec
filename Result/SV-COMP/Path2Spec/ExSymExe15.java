class Main {
  static int field;
  static int field2;
  //@ requires arg != null && arg < 0;
  //@ ensures \result == true;
  //@ also
  //@ requires arg != null && 0 <= arg.shortValue() && arg.shortValue() <= Integer.MAX_VALUE - 13000;
  //@ ensures \result == true;
  //@ also
  //@ requires arg != null;
  public static boolean f(Short arg) {
    int x = 13000;
    Main inst = new Main();
    field = arg;
    if (field < 0)
      return true;
    return inst.test(x, field, field2);
  }
  //@ requires x == 13000 && 0 <= z && z <= Integer.MAX_VALUE - 13000;
  //@ ensures \result == true;
  //@ also
  //@ requires Integer.MIN_VALUE <= x + z && x + z <= Integer.MAX_VALUE;
  //@ requires Integer.MIN_VALUE + 7 <= x;
  //@ ensures \old(x) + \old(z) <= 99 ==> \result == false;
  //@ ensures \old(x) + \old(z) > 99 ==> \result == true;
  //@ also
  //@ requires x + z > 99;
  //@ ensures \result == true;
  //@ also
  //@ ensures (\old(x + z) > 99) ==> \result;
  public boolean test(int x, int z, int r) {
    System.out.println("Testing ExSymExe15");
    int y = 3;
    r = x + z;
    z = x - y - 4;
    if (r <= 99) {
      System.out.println("branch FOO1");
      return false;
    } else System.out.println("branch FOO2");
    if (x <= z) System.out.println("branch BOO1");
    else System.out.println("branch BOO2");
    return true;
  }
}
