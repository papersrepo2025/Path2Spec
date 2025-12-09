class A extends RuntimeException {}

class B extends A {}

public class exception18 {
  //@ ensures false;
  //@ signals_only A;
  //@ signals (A e) true;
  private /*@ spec_public @*/ static void foo() {
    A a = new A();
    throw a;
  }

  //@ ensures true;
  //@ signals (Exception e) false;
  public static void main(String[] args) {
    try {
      foo();
      //@ assume false; // unreachable since foo never returns normally
      assert false;
    } catch (B e) {
      assert false;
    } catch (A e) {
      // expected here
    }
  }
}