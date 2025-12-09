class Main {

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
  //@ requires z > 0;
  //@ ensures \result == true;
  //@ also
  //@ requires z <= 0;
  //@ ensures \result == false;
  //@ also
  //@ ensures \result <==> \old(z) > 0;
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