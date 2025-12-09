class Main {
  static int field;
  static int field2;

  public static boolean f(int arg) {
    int x = 3; /* we want to specify in an annotation that this param should be
                  symbolic */

    Main inst = new Main();
    field = arg;
    if (field < 0) return true;
    field = field % 10;
    return inst.test(x, field, field2);
  }

  //@ requires z + r > 99;
//@ ensures \result == false;
//@ also
//@ requires true;
//@ ensures true;
  public boolean test(int x, int z, int r) {
    //@ assume System.out != null;
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