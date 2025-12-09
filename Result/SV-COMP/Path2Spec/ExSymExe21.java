class Main {
  static int field;
  static int field2;
  //@ requires System.out != null;
  //@ ensures \result == true;
  public static boolean f() {
    int x = 3; /* we want to specify in an annotation that this param should be
                  symbolic */

    Main inst = new Main();
    field = 9;
    return inst.test(x, field, field2);
  }
  //@ requires System.out != null;
  //@ requires x >= -2147483641;
  //@ requires Integer.MIN_VALUE <= x + z && x + z <= Integer.MAX_VALUE;
  //@ ensures \result == true;
  //@ also
  //@ requires Integer.MIN_VALUE <= x + z && x + z <= Integer.MAX_VALUE;
  //@ requires Integer.MIN_VALUE + 7 <= x;
  //@ ensures \result == true;
  public boolean test(int x, int z, int r) {
    System.out.println("Testing ExSymExe21");
    int y = 3;
    r = x + z;
    z = x - y - 4;
    if (r >= 99) System.out.println("branch FOO1");
    else System.out.println("branch FOO2");
    if (x >= z) System.out.println("branch BOO1");
    else {
      return false;
    }
    return true;
  }
}
