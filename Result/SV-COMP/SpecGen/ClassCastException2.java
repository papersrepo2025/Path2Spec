class A {}

class B extends A {}

class C extends B {}

public class ClassCastException2 {
  //@ ensures \result;
  public static boolean f() {
    try {
      A c = new C();
      B b = (B) c;
    } catch (ClassCastException exc) {
      return false;
    }
    return true;
  }
}