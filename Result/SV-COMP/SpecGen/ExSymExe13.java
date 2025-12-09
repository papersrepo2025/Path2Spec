class Main {
  static /*@ spec_public @*/ int field;
  static /*@ spec_public @*/ int field2;

  /*@ ensures \result == true;
    @ ensures (arg >= 0 ==> Main.field == 9) && (arg < 0 ==> Main.field == \old(Main.field));
    @ ensures Main.field2 == \old(Main.field2);
    @*/
  public static boolean f(int arg) {
    if (arg < 0)
      return true;
    int x = arg;

    Main inst = new Main();
    field = 9;
    return inst.test(x, arg, field2);
  }
  
  /*@ ensures \result <==> x >= Integer.MIN_VALUE + 7;
    @ ensures Main.field == \old(Main.field) && Main.field2 == \old(Main.field2);
    @*/
  public boolean test(int x, int z, int r) {
    System.out.println("Testing ExSymExe13");
    int y = 3;
    r = x + z;
    z = x - y - 4;
    if (r < 99) System.out.println("branch FOO1");
    else System.out.println("branch FOO2");
    if (x < z) {
      System.out.println("branch BOO1");
      return false;
    } else System.out.println("branch BOO2");

    return true;
  }
}