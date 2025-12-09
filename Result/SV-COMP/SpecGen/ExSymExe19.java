class Main {
  /*@ spec_public @*/ static int field;
  /*@ spec_public @*/ static int field2;

  //@ ensures arg < 0 ==> (\result && Main.field == arg && Main.field2 == \old(Main.field2));
  //@ ensures arg >= 0 ==> (Main.field == arg % 10 && Main.field2 == \old(Main.field2) && \result == ((arg % 10) + \old(Main.field2) <= 99));
  public static boolean f(int arg) {
    int x = 3; /* we want to specify in an annotation that this param should be
                  symbolic */

    Main inst = new Main();
    field = arg;
    if (field < 0) return true;
    field = field % 10;
    return inst.test(x, field, field2);
  }

  //@ ensures \result == (z + r <= 99);
  //@ ensures Main.field == \old(Main.field) && Main.field2 == \old(Main.field2);
  public boolean test(int x, int z, int r) {
    System.out.println("Testing ExSymExe19");
    int y = 3;
    x = z + r;
    z = y * x;
    r = -z;
    if (x > 99) {
      System.out.println("branch FOO1");
      return false;
    } else System.out.println("branch FOO2");
    if (r > z) System.out.println("branch BOO1");
    else System.out.println("branch BOO2");
    return true;
  }
}