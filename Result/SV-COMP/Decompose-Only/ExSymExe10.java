class Main {
  static int field;

  //@ assignable \everything;
  //@ ensures \result;
  public static boolean f() {
    int x = 3; /* we want to specify in an annotation that this param should be
                  symbolic */

    Main inst = new Main();
    field = 9;
    return inst.test(x, field);
  }

  //@ assignable \everything;
  //@ requires -(x * z) + 3 <= 0;
  //@ ensures \result;
  //@ also
  //@ requires -(x * z) + 3 > 0;
  //@ ensures !\result;
  public boolean test(int x, int z) {
    System.out.println("Testing ExSymExe10");
    int y = 3;
    x = x * z;
    z = -x + y;
    if (z <= 0) System.out.println("branch FOO1");
    else {
      System.out.println("branch FOO2");
      return false;
    }
    if (x <= 0) System.out.println("branch BOO1");
    else System.out.println("branch BOO2");
    return true;
  }
}