class Main {
  //@ requires true;
  //@ ensures \result == true;
  //@ also
  //@ requires System.out != null;
  //@ ensures \result == true;
  public static boolean f() {
    int x = 3;
    int y = 5;
    Main inst = new Main();
    return inst.test(x, y, 9);
  }

  /*
   * test IF_ICMPNE  bytecode  (Note: javac compiles "==" to IF_ICMPNE)
   */
  //@ requires true;
  //@ ensures \result == (z != x);
  //@ also
  //@ requires System.out != null;
  //@ requires z != x;
  //@ ensures \result == true;
  public boolean test(int x, int z, int r) {
    System.out.println("Testing ExSymExe28");
    if (z == x) {
      return false;
    } else System.out.println("branch FOO2");
    if (x == r) System.out.println("branch BOO1");
    else
      System.out.println("branch BOO2");
    return true;
  }
}
