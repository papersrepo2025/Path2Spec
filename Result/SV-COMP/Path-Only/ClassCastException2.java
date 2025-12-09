class A {}

class B extends A {}

class C extends B {}

public class ClassCastException2 {
  //@ requires true;
  //@ ensures \result == true;
  public static boolean f() {
    try {
      A c = new C();
      //@ assert c instanceof B;
      B b = (B) c;
    } catch (ClassCastException exc) {
      return false;
    }
    return true;
  }
}