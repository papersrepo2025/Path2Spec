class A {
  int i;
}

class NullPointerException2 {
  //@ requires false;
//@ ensures \result == true;
  public static boolean f() {
    A a = null;
    try {
      a.i = 0;
    } catch (NullPointerException exc) {
      return false;
    }
    return true;
  }
}