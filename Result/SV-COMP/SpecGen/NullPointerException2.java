class A {
  /*@ spec_public @*/ int i;
}

class NullPointerException2 {
  /*@ public normal_behavior
    @   assignable \nothing;
    @   ensures \result == false;
    @*/
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