class Main {
  static int field;
  //@ requires true;
  //@ ensures \result == true;
  //@ also
  //@ requires System.out != null;
  //@ ensures \result == true;
  //@ also
  //@ requires System.out != null;
  public static boolean f() {
    int x = 3; 
    Main inst = new Main();
    field = 9;
    return inst.test(x, field);
  }
  //@ requires true;
  //@ ensures \result == (x < 7);
  //@ also
  //@ requires System.out != null;
  //@ requires Integer.MIN_VALUE + 7 <= x && x < 7;
  //@ ensures \result == true;
  //@ also
  //@ requires System.out != null && x >= 7;
  //@ ensures \result == false;
  //@ also
  //@ requires System.out != null;
  //@ ensures true;
  //@ also
  //@ ensures \result == (x < 7);
  public boolean test(int x, int z) {
    System.out.println("Testing ExSymExe9");
    int y = 3;
    z = x - y - 4;
    if (z < 0) System.out.println("branch FOO1");
    else {
      System.out.println("branch FOO2");
      return false;
    }
    if (y < 0) System.out.println("branch BOO1");
    else System.out.println("branch BOO2");

    return true;
  }
}
