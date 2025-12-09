class Main {
  /*@ spec_public @*/ static int field;
  /*@ spec_public @*/ static int field2;

  //@ requires arg != null;
  //@ ensures Main.field == arg.shortValue();
  //@ ensures \result;
  public static boolean f(Short arg) {
    int x = 13000;
    Main inst = new Main();
    field = arg;
    if (field < 0)
      return true;
    return inst.test(x, field, field2);
  }
  
  //@ ensures \result <==> \old(x) + \old(z) > 99;
  public boolean test(int x, int z, int r) {
    System.out.println("Testing ExSymExe15");
    int y = 3;
    r = x + z;
    z = x - y - 4;
    if (r <= 99) {
      System.out.println("branch FOO1");
      return false;
    } else System.out.println("branch FOO2");
    if (x <= z) System.out.println("branch BOO1");
    else System.out.println("branch BOO2");
    return true;
  }
}