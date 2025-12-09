class Main {
  /*@ public normal_behavior
    @   ensures \result <==> (\old(z) + 2 > 0);
    @*/
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