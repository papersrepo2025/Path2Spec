class Main {

  //@ ensures \result;
  public static boolean f() {
    int a = 3;
    Main inst = new Main();
    int b = 8;
    return inst.test(a, b, a);
  }

  //@ ensures \result <==> \old(x) > -3;
  public boolean test(int x, int y, int z) {
    System.out.println("Testing ExSymExe25");
    y = z + 1;
    z = z + x;
    x = x + 3;
    if (z > 0) System.out.println("branch FOO1");
    else System.out.println("branch FOO2");
    if (x > 0) System.out.println("branch BOO1");
    else {
      return false;
    }

    return true;
  }
}