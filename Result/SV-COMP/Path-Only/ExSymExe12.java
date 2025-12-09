class Main {
  static int field;
  static int field2;
  //@ requires System.out != null;
  //@ ensures true;
  //@ also
  //@ requires System.out != null;
  //@ ensures \result == true;
  public static boolean f(int arg) {
    int x = 3; /* we want to specify in an annotation that this param should be
                  symbolic */

    Main inst = new Main();
    field = 9;
    if (arg < 0) return true;
    return inst.test(x, arg, field2);
    // test(x,x);
  }
  /* we want to let the user specify that this method should be symbolic */

  /*
   * test IF_ICMPGE, IADD & ISUB  bytecodes
   */
  //@ requires System.out != null && x <= -3;
  //@ ensures \result == false;
  //@ also
  //@ requires System.out != null;
  //@ ensures true;
  //@ also
  //@ requires System.out != null;
  //@ requires x > -3;
  //@ ensures \result == true;
  public boolean test(int x, int z, int r) {
    System.out.println("Testing ExSymExe12");
    int y = 3;
    r = x + z;
    x = z - y;
    z = r;
    if (z < x) System.out.println("branch FOO1");
    else System.out.println("branch FOO2");
    if (x < r) System.out.println("branch BOO1");
    else {
      System.out.println("branch BOO2");
      return false;
    }
    return true;
  }
}
