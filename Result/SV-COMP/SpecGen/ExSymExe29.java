class Main {

  /*@ ensures \result; @*/
  public static boolean f() {
    int x = 3;
    int y = 5;
    Main inst = new Main();
    return inst.test(x, y, 9);
  }

  /*@ ensures \result <==> x != r; @*/
  public boolean test(int x, int z, int r) {
    System.out.println("Testing ExSymExe29");
    if (z != x) System.out.println("branch FOO1");
    else System.out.println("branch FOO2");
    if (x != r) System.out.println("branch BOO1");
    else {
      return false;
    }
    return true;
  }
}