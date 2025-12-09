class Main {
  //@ requires true;
  //@ ensures \result == true;
  //@ also
  //@ requires System.out != null;
  //@ ensures \result == true;
  public static boolean f() {
    int x = 3;
    boolean y = true;
    Main inst = new Main();
    return inst.test(y, x);
  }

  /*
   * test IINC & IFLE bytecodes (Note: javac compiles ">" to IFLE)
   */
  //@ requires true;
  //@ ensures \result == x;
  //@ also
  //@ requires System.out != null;
  //@ ensures \result == x;
  //@ also
  //@ requires System.out != null;
  //@ requires z < Integer.MAX_VALUE;
  //@ requires !x;
  //@ ensures \result == false;
  //@ also
  //@ requires System.out != null;
  //@ requires z < Integer.MAX_VALUE;
  //@ requires x;
  //@ ensures true;
  public boolean test(boolean x, int z) {
    System.out.println("Testing ExSymExeBool");
    z++;
    if (x) System.out.println("branch FOO1");
    else {
      return false;
    }
    return true;
  }
}
