class Main {
  static int field;
  static int field2;
  //@ requires System.out != null;
  //@ ensures \result == true;
  //@ ensures (\old(arg + arg) < 99) ==> \result == true;
  //@ also
  //@ requires arg < 0;
  //@ ensures \result == true;
  //@ also
  //@ ensures \result == true;
  public static boolean f(int arg) {
    if (arg < 0)
      return true;
    int x = arg;

    Main inst = new Main();
    field = 9;
    return inst.test(x, arg, field2);
  }
  //@ requires System.out != null;
  //@ ensures \result == true;
  //@ ensures (\old(x + z) < 99) ==> \result == true;
  //@ also
  //@ requires System.out != null;
  //@ ensures \result == true;
  //@ also
  //@ ensures \result == true;
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
