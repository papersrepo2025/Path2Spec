class Main {
  //@ requires true;
  //@ ensures \result == true;
  //@ also
  //@ requires System.out != null;
  //@ ensures true;
  //@ also
  //@ ensures \result == true;
  public static boolean f() {
    int a = 3;
    Main inst = new Main();
    int b = 8;
    return inst.test(a, b, a);
  }
  //@ requires 0 <= z && z < Integer.MAX_VALUE && x > 0;
  //@ ensures \result == true;
  //@ also
  //@ requires System.out != null;
  //@ ensures true;
  //@ also
  //@ requires System.out != null && (z <= -1 || z == Integer.MAX_VALUE);
  //@ ensures \result == false;
  //@ also
  //@ requires 0 <= z && z <= 2147483646 && x > 0;
  //@ ensures \result == true;
  //@ also
  //@ requires System.out != null && z < Integer.MAX_VALUE;
  //@ ensures true;
  //@ also
  //@ requires System.out != null && 0 <= z && z <= 2147483646 && x <= 0;
  //@ ensures \result == true;
  public boolean test(int x, int y, int z) {
    System.out.println("Testing ExSymExe26");
    y = x;
    z++;
    if (z > 0) System.out.println("branch FOO1");
    else {
      return false;
    }
    if (y > 0) System.out.println("branch BOO1");
    else System.out.println("branch BOO2");

    return true;
  }
}
