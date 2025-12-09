class Main {
  //@ requires z + 2 > 0;
  //@ ensures \result == true;
  //@ also
  //@ requires z + 2 <= 0;
  //@ ensures \result == false;
  //@ also
  //@ ensures \result <==> \old(z) + 2 > 0;
  public boolean test(int x, int z) {
    System.out.println("Testing ExSymExe3");
    //@ assert z == \old(z);
    int y = 3;
    //@ assert y == 3;
    //@ assert z == \old(z);
    z++;
    //@ assert z == \old(z) + 1;
    x = ++z;
    //@ assert x == z && x == \old(z) + 2;
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