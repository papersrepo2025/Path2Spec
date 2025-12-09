class Main {
  static int field;
  int field2;
  //@ requires arg != 2;
  //@ ensures \result == true;
  public static boolean f(int arg) {
    if (arg == 2)
      return (new Main()).test();
    return true;
  }
  public boolean test() {
    if (field == 0 && field2 == 0) System.out.println("br 0");
    else {
      return false;
    }
    return true;
  }
}
