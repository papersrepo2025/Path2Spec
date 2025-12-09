class Main {
  /*@ spec_public @*/ static int field;

  //@ ensures \result == true;
  //@ ensures Main.field == 9;
  public static boolean f() {
    int x = 3;
    Main inst = new Main();
    field = 9;
    return inst.test(x, field);
  }

  //@ ensures (\result <==> x != 4);
  //@ ensures Main.field == \old(Main.field);
  public boolean test(int x, int z) {
    System.out.println("Testing ExSymExe17");
    int y = 0;
    z = x - y - 4;
    if (z == 0) {
      System.out.println("branch FOO1");
      return false;
    } else System.out.println("branch FOO2");
    if (y == 0) System.out.println("branch BOO1");
    else
      System.out.println("branch BOO2");
    return true;
  }
}