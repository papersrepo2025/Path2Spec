class A extends RuntimeException {}

class B extends A {}

class C extends B {}

public class exceptions16 {
  //@ ensures \result == false;
  //@ assignable \nothing;
  public static boolean f(int x) {
    try {
      x++;
    } catch (A exc) {
      return false;
    }

    try {
      throw new B();
    } catch (B exc) {
      return false;
    }

    // Unreachable by Java semantics; commented out to avoid compile-time error
    // return true;
  }
}