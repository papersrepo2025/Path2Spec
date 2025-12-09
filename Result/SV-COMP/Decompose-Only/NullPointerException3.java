class A {
  int i;
}

public class NullPointerException3 {
  //@ assignable \nothing;
  //@ ensures \result == false;
  public static boolean f() {
    /*@ nullable @*/ A a = null;
    try {
      int i = a.i;
    } catch (NullPointerException exc) {
      return false;
    }
    return true;
  }
}