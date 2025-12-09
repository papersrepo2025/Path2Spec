class Main {
  /*@ spec_public @*/ static int field;
  /*@ spec_public @*/ static int field2;

  //@ requires arg != null;
  //@ ensures \result;
  //@ ensures (arg < 0) ==> field == \old(field);
  //@ ensures (arg >= 0) ==> field == arg;
  //@ ensures field2 == \old(field2);
  public static boolean f(Short arg) {
    if (arg < 0)
      return true;
    int x = arg;

    Main inst = new Main();
    field = arg;
    return inst.test(x, arg, field2);
  }
  
  //@ ensures \result <==> \old(x) > -3;
  //@ ensures field == \old(field) && field2 == \old(field2);
  public boolean test(int x, int z, int r) {
    System.out.println("Testing ExSymExe14");
    int y = 3;
    r = x + z;
    x = z - y;
    z = r;
    if (z <= x) {
      System.out.println("branch FOO1");
      return false;
    } else System.out.println("branch FOO2");
    if (x <= r) System.out.println("branch BOO1");
    else System.out.println("branch BOO2");

    return true;
  }
}