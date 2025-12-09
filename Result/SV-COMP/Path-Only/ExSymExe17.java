class Main {
  static int field;
  //@ requires System.out != null;
  //@ ensures \result == true;
  //@ also
  //@ ensures \result == true;
  public static boolean f() {
    int x = 3;
    Main inst = new Main();
    field = 9;
    return inst.test(x, field);
  }
  //@ requires System.out != null;
  //@ ensures \result == (x != 4);
  //@ also
  //@ ensures x == 4 ==> \result == false;
  //@ ensures x != 4 ==> \result == true;
  public boolean test(int x, int z) {
    System.out.println("Testing ExSymExe17");
    int y = 0;
    z = x - y - 4;
    if (z == 0) {
      System.out.println("branch FOO1");
      return false;
    } else System.out.println("branch FOO2");
    if (y == 0) System.out.println("branch BOO1");
    else
      System.out.println("branch BOO2");
    return true;
  }
}
