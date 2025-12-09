class Main {
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