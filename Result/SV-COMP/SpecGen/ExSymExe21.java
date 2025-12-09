class Main {
  /*@ spec_public @*/ static int field;
  /*@ spec_public @*/ static int field2;

  /*@ public normal_behavior
    @   assignable field;
    @   ensures field == 9 && \result == true && field2 == \old(field2);
    @*/
  public static boolean f() {
    int x = 3; /* we want to specify in an annotation that this param should be
                  symbolic */

    Main inst = new Main();
    field = 9;
    return inst.test(x, field, field2);
  }

  /*@ public normal_behavior
    @   assignable \nothing;
    @   ensures \result <==> x >= x - 7;
    @*/
  public boolean test(int x, int z, int r) {
    System.out.println("Testing ExSymExe21");
    int y = 3;
    r = x + z;
    z = x - y - 4;
    if (r >= 99) System.out.println("branch FOO1");
    else System.out.println("branch FOO2");
    if (x >= z) System.out.println("branch BOO1");
    else {
      return false;
    }
    return true;
  }
}