class Main {
  //@ requires z < 2;
  //@ requires z >= Integer.MIN_VALUE + 3;
  //@ requires java.lang.System.out != null;
  //@ ensures \result == false;
  //@ also
  //@ requires System.out != null;
  //@ ensures \result == (\old(z) >= 2);
  //@ also
  //@ requires z == 2;
  //@ ensures \result == true;
  //@ also
  //@ requires System.out != null;
  //@ requires z >= 3;
  //@ ensures \result == true;
  public boolean test(int x, int z) {
    System.out.println("Testing ExSymExe8");
    int y = 3;
    int p = 2;
    x = z - y;
    z = z - p;
    if (z < 0) {
      System.out.println("branch FOO1");
      return false;
    } else System.out.println("branch FOO2");
    if (x < 0) System.out.println("branch BOO1");
    else
      System.out.println("branch BOO2");
    return true;
  }
}
