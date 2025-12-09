class Main {
  static int field;
  static int field2;
  //@ requires true;
  //@ ensures \result == true;
  //@ also
  //@ requires System.out != null;
  //@ ensures \result == true;
  public static boolean main() {
    int x = 3;
    Main inst = new Main();
    field = 9;
    return inst.test(x, field, field2);
  }
  //@ requires this != null && x == 3 && z == 9;
  //@ ensures \result == true;
  //@ also
  //@ ensures \old(x) == -3 ==> \result == false;
  //@ also
  //@ requires System.out != null;
  //@ requires x > -3;
  //@ requires Integer.MIN_VALUE <= x + z && x + z <= Integer.MAX_VALUE;
  //@ requires Integer.MIN_VALUE <= z - 3 && z - 3 <= Integer.MAX_VALUE;
  //@ ensures \result == true;
  //@ also
  //@ requires System.out != null;
  //@ ensures (\old(x) <= -3) ==> \result == false;
  //@ ensures (\old(x) > -3) ==> \result == true;
  public boolean test(int x, int z, int r) {
    System.out.println("Testing ExSymExe20");
    int y = 3;
    r = x + z;
    x = z - y;
    z = r;
    if (z >= x) System.out.println("branch FOO1");
    else System.out.println("branch FOO2");
    if (x >= r) {
      return false;
    } else System.out.println("branch BOO2");

    return true;
  }
}
