class Main {
  /*@ 
    @ ensures (z == 0) ==> (\result == false);
    @ ensures (z != 0) ==> (\result == true);
    @*/
  public boolean test(int x, int z) {
    System.out.println("Testing ExSymExe6");
    int y = 0;
    x = z - y;
    if (z != 0) System.out.println("branch FOO1");
    else {
      System.out.println("branch FOO2");
      return false;
    }
    if (x != 0) System.out.println("branch BOO1");
    else
      System.out.println("branch BOO2");
    return true;
  }
}