class Main {

  //@ ensures \result == true;
  /*@ spec_public @*/ public static boolean f() {
    int x = 3;
    int y = 5;
    Main inst = new Main();
    return inst.test(x, y, 9);
  }

  /*
   * test IF_ICMPNE  bytecode  (Note: javac compiles "==" to IF_ICMPNE)
   */
  //@ ensures \result <==> (z != x);
  /*@ spec_public @*/ public boolean test(int x, int z, int r) {
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