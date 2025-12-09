class Main {
  //@ ensures \result == (x != 7);
  public boolean test(int x, int z) {
    System.out.println("Testing ExSymExe7");
    int y = 3;
    z = x - y - 4;
    if (z != 0) System.out.println("branch FOO1");
    else {
      System.out.println("branch FOO2");
      return false;
    }
    if (y != 0) System.out.println("branch BOO1");
    else
      System.out.println("branch BOO2");
    return true;
  }
}