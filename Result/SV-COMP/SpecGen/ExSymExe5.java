class Main {
  //@ ensures z == \old(x) + 7;
  //@ ensures \result;
  public boolean test(int x, int z) {
    System.out.println("Testing ExSymExe5");
    int y = 3;
    z = x + y + 4;
    if (z >= 0) System.out.println("branch FOO1");
    else System.out.println("branch FOO2");
    if (y >= 0) System.out.println("branch BOO1");
    else {
      System.out.println("branch BOO2");
      return false;
    }
    return true;
  }
}