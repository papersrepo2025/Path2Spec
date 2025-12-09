class Main {
  static /*@ spec_public @*/ int field;
  static /*@ spec_public @*/ int field2;

  /*@ ensures field == 9;
    @ ensures field2 == \old(field2);
    @ ensures \result <==> (\old(field2) <= 0);
    @*/
  public static boolean f() {
    int x = 3;
    Main inst = new Main();
    field = 9;
    return inst.test(x, field, field2);
  }
  
  /*@ ensures \result <==> !((\old(z) * (\old(x) * \old(r))) > (\old(x) * \old(r)));
    @*/
  public boolean test(int x, int z, int r) {
    System.out.println("Testing ExSymExe18");
    int y = 3;
    x = x * r;
    z = z * x;
    r = y * x;
    if (z > x) {
      System.out.println("branch FOO1");
      return false;
    } else System.out.println("branch FOO2");
    if (x > r) System.out.println("branch BOO1");
    else System.out.println("branch BOO2");
    return true;
  }
}