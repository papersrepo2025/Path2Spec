class Main {
  static int field;
  //@ requires true;
  //@ ensures \result == true;
  //@ also
  //@ requires System.out != null;
  //@ ensures \result == true;
  public static boolean f() {
    int x = 3; /* we want to specify in an annotation that this param should be
                  symbolic */

    Main inst = new Main();
    field = 9;
    return inst.test(x, field);
  }
  //@ ensures (\old(x) * \old(z) >= 3) ==> \result;
  //@ also
  //@ ensures (\old(x) * \old(z) < 3 && \old(x) * \old(z) >= -2147483644) ==> \result == false;
  //@ ensures (\old(x) * \old(z) >= 3) ==> \result == true;
  //@ also
  //@ requires System.out != null;
  //@ requires x * z >= 3;
  //@ ensures \result == true;
  //@ also
  //@ requires (long)x * (long)z >= 3;
  //@ requires Integer.MIN_VALUE <= (long)x * (long)z && (long)x * (long)z <= Integer.MAX_VALUE;
  //@ ensures \result == true;
  public boolean test(int x, int z) {
    System.out.println("Testing ExSymExe10");
    int y = 3;
    x = x * z;
    z = -x + y;
    if (z <= 0) System.out.println("branch FOO1");
    else {
      System.out.println("branch FOO2");
      return false;
    }
    if (x <= 0) System.out.println("branch BOO1");
    else System.out.println("branch BOO2");
    return true;
  }
}
