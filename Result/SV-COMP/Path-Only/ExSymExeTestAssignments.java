class Main {
  int field;
  //@ requires x < 0;
  //@ ensures \result == true;
  //@ also
  //@ requires x >= 0;
  //@ requires System.out != null;
  //@ ensures \result == true;
  //@ also
  //@ requires true;
  //@ ensures \result == true;
  public static boolean f (int x) {
    if (x < 0) return true;
    return test(x);
  }
  //@ requires System.out != null;
  //@ ensures \result == true;
  //@ also
  //@ requires true;
  //@ ensures \result == true;
  public static boolean test(int x) {
    x = 3;
    if (x > 0) System.out.println("branch BOO1");
    else {
      return false;
    }
    return true;
  }
}
