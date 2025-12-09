class Main {
  /*@ spec_public @*/ static int field;
  /*@ spec_public @*/ static int field2;

  //@ ensures \result == true;
  //@ ensures field == 9;
  //@ ensures field2 == \old(field2);
  public static boolean main() {
    int x = 3;
    Main inst = new Main();
    field = 9;
    return inst.test(x, field, field2);
  }

  //@ ensures \result <==> (\old(x) > -3);
  //@ ensures Main.field == \old(Main.field);
  //@ ensures Main.field2 == \old(Main.field2);
  public boolean test(int x, int z, int r) {
    System.out.println("Testing ExSymExe20");
    int y = 3;
    r = x + z;
    x = z - y;
    z = r;
    if (z >= x) System.out.println("branch FOO1");
    else System.out.println("branch FOO2");
    if (x >= r) {
      return false;
    } else System.out.println("branch BOO2");

    return true;
  }
}