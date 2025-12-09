class Main {
  //@ requires z >= -1;
  //@ ensures \result == true && \old(z) >= -1;
  //@ also
  //@ requires z <= -2;
  //@ ensures \result == false && \old(z) <= -2;
  public boolean test(int x, int z) {
    System.out.println("Testing ExSymExe2");
    //@ assert z == \old(z);
    z++;
    //@ assert z == \old(z) + 1;
    x = ++z;
    if (z > 0) System.out.println("branch FOO1");
    else {
      System.out.println("branch FOO2");
      return false;
    }
    if (x > 0) System.out.println("branch BOO1");
    else {
      System.out.println("branch BOO2");
      return false;
    }
    return true;
  }
}