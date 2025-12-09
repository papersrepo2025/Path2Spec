class Main {
  //@ spec_public; static int field;
  //@ spec_public; static int field2;

  //@ requires 0 < field2 && field2 <= 79536431;
//@ ensures \result == false;
  public static boolean f() {
    int x = 3;
    Main inst = new Main();
    field = 9;
    return inst.test(x, field, field2);
  }
  
  //@ requires x == 3;
//@ requires z == 9;
//@ requires 0 <= r && r <= 79536431;
//@ ensures (\old(r) > 0) ==> \result == false;
  public boolean test(int x, int z, int r) {
    System.out.println("Testing ExSymExe18");
    int y = 3;
    x = x * r;
    z = z * x;
    r = y * x;
    if (z > x) {
      System.out.println("branch FOO1");
      return false;
    } else System.out.println("branch FOO2");
    if (x > r) System.out.println("branch BOO1");
    else System.out.println("branch BOO2");
    return true;
  }
}