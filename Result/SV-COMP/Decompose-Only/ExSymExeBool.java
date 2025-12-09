class Main {

  public static boolean f() {
    int x = 3;
    boolean y = true;
    Main inst = new Main();
    return inst.test(y, x);
  }

  /*
   * test IINC & IFLE bytecodes (Note: javac compiles ">" to IFLE)
   */
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