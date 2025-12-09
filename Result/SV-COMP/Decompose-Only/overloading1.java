class other_class {}
;

class Main {
  //@ assignable \nothing;
  //@ ensures \result == false;
  public static boolean f() {
    if(f(1) == 1) return false;
    if(f(1.0d) == 2) return false;
    if (f(new other_class()) == 3) return false;
    return true;
  }

  //@ assignable \nothing;
  //@ ensures \result == 1;
  static /*@ pure @*/ int f(int i) {
    return 1;
  }

  //@ assignable \nothing;
  //@ ensures \result == 2;
  static /*@ pure @*/ int f(double d) {
    return 2;
  }

  //@ assignable \nothing;
  //@ ensures \result == 3;
  static /*@ pure @*/ int f(other_class o) {
    return 3;
  }
}