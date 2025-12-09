class other_class {}
;

class Main {
  //@ requires true;
  //@ ensures \result == false;
  public static boolean f() {
    if(f(1) == 1) return false;
    if(f(1.0d) == 2) return false;
    if (f(new other_class()) == 3) return false;
    return true;
  }
  //@ requires true;
  //@ ensures \result == 1;
  static int f(int i) {
    return 1;
  }
  //@ requires true;
  //@ ensures \result == 2;
  static int f(double d) {
    return 2;
  }
  //@ requires true;
  //@ ensures \result == 3;
  static int f(other_class o) {
    return 3;
  }
}
