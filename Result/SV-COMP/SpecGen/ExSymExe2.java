class Main {
  //@ ensures x == z;
  //@ ensures \result <==> (z > 0 && x > 0);
  public boolean test(int x, int z) {
    System.out.println("Testing ExSymExe2");
    z++;
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