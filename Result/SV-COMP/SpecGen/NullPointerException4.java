class A {
  /*@ spec_public @*/ int i;
}

public class NullPointerException4 {
  //@ ensures \result == false;
  public static boolean f() {
    A a = null;
    try {
      a.i = 0;
    } catch (Exception exc) {
      return false;
    }
    return true;
  }
}