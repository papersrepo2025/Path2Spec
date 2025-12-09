class Main {

  //@ ensures \result;
  public static boolean f() {
    int a = 3;
    Main inst = new Main();
    int b = 8;
    return inst.test(a, b, a);
  }

  //@ ensures y == x;
  //@ ensures z == \old(z) + 1;
  //@ ensures \result == (\old(z) >= 0);
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