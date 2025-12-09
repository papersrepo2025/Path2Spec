class A {}

class B {}

public class ClassCastException3 {
  //@ ensures \result == true || \result == false;
  public static boolean f() {
    try {
      Object a = new A();
      /*@ assume a instanceof B; @*/
      B b = (B) a;
    } catch (Exception exc) {
      return false;
    }
    return true;
  }
}