class Main {
  //@ requires -1 <= z && z <= 2147483645;
  //@ ensures \result == true;
  //@ also
  //@ requires -1 <= z && z <= 2147483645;
  //@ requires System.out != null;
  //@ ensures \result == true;
  //@ also
  //@ requires System.out != null;
  //@ requires z <= -2 || z >= 2147483646;
  //@ ensures \result == false;
  //@ also
  //@ requires (z <= -2 || z >= 2147483646) && System.out != null;
  //@ ensures \result == false;
  public boolean test(int x, int z) {
    System.out.println("Testing ExSymExe3");
    int y = 3;
    z++;
    x = ++z;
    if (x > 0) System.out.println("branch FOO1");
    else {
      System.out.println("branch FOO2");
      return false;
    }
    if (y > 0) System.out.println("branch BOO1");
    else
      System.out.println("branch BOO2");
    return true;
  }
}
