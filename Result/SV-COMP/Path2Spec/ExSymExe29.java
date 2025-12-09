class Main {
  //@ requires true;
  //@ ensures \result == true;
  //@ also
  //@ requires System.out != null;
  //@ ensures \result == true;
  //@ also
  //@ requires System.out != null;
  public static boolean f() {
    int x = 3;
    int y = 5;
    Main inst = new Main();
    return inst.test(x, y, 9);
  }
  //@ requires true;
  //@ ensures (x == r) ==> \result == false;
  //@ ensures (x != r) ==> \result == true;
  //@ also
  //@ requires System.out != null;
  //@ requires z != x && x != r;
  //@ ensures \result == true;
  //@ also
  //@ requires true;
  //@ ensures (z != x) && (x == r) ==> \result == false;
  //@ also
  //@ requires System.out != null;
  //@ ensures true;
  //@ also
  //@ requires System.out != null && z == x && x != r;
  //@ ensures \result == true;
  //@ also
  //@ ensures (z == x && x == r) ==> \result == false;
  public boolean test(int x, int z, int r) {
    System.out.println("Testing ExSymExe29");
    if (z != x) System.out.println("branch FOO1");
    else System.out.println("branch FOO2");
    if (x != r) System.out.println("branch BOO1");
    else {
      return false;
    }
    return true;
  }
}
