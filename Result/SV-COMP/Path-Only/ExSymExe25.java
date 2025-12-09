class Main {
  //@ ensures \result == true;
  //@ also
  //@ requires System.out != null;
  //@ also
  //@ requires true;
  //@ ensures \result == true;
  public static boolean f() {
    int a = 3;
    Main inst = new Main();
    int b = 8;
    return inst.test(a, b, a);
  }
  //@ requires x <= Integer.MAX_VALUE - 3;
  //@ requires z < Integer.MAX_VALUE;
  //@ requires Integer.MIN_VALUE - x <= z && z <= Integer.MAX_VALUE - x;
  //@ ensures \result == (\old(x) + 3 > 0);
  //@ also
  //@ ensures ((\old(z) + \old(x) <= 0) && (\old(x) + 3 > 0)) ==> \result == true;
  //@ also
  //@ requires true;
  //@ ensures ((\old(z + x) > 0) && (\old(x) + 3 <= 0)) ==> \result == false;
  //@ also
  //@ requires (z + x > 0) && (x + 3 <= 0);
  //@ ensures \result == false;
  //@ also
  //@ requires System.out != null && z + x <= 0 && x + 3 <= 0;
  //@ ensures \result == false;
  //@ also
  //@ requires System.out != null;
  //@ ensures true;
  //@ also
  //@ requires java.lang.System.out != null;
  //@ ensures (x > 0) ==> \result == true;
  public boolean test(int x, int y, int z) {
    System.out.println("Testing ExSymExe25");
    y = z + 1;
    z = z + x;
    x = x + 3;
    if (z > 0) System.out.println("branch FOO1");
    else System.out.println("branch FOO2");
    if (x > 0) System.out.println("branch BOO1");
    else {
      return false;
    }

    return true;
  }
}
