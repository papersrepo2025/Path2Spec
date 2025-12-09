class Main {
  //@ requires true;
  //@ ensures \result == true;
  //@ also
  //@ ensures \result == true;
  public static boolean f() {
    int a = 3;
    Main inst = new Main();
    int b = 8;
    return inst.test(a, b, a);
  }

  /*
   * test concrete = symbolic
   * (con#sym#sym)
   */
  //@ requires System.out != null;
  //@ ensures (z > 0) ==> \result == true;
  //@ ensures (z <= 0) ==> \result == false;
  //@ also
  //@ requires Integer.MIN_VALUE <= z + z && z + z <= Integer.MAX_VALUE;
  //@ ensures z <= 0 ==> \result == false;
  //@ ensures z > 0 ==> \result == true;
  //@ also
  //@ requires z > 0 && z <= Integer.MAX_VALUE / 2;
  //@ ensures \result == true;
  public boolean test(int x, int y, int z) {
    System.out.println("Testing ExSymExe27");
    x = z;
    y = z + x;
    if (x < y) System.out.println("branch FOO1");
    else {
      return false;
    }
    if (z > 0) System.out.println("branch BOO1");
    else System.out.println("branch BOO2");

    return true;
  }
}
