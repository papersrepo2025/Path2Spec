interface Interface {
  //@ ensures true;
  /*@ pure @*/ boolean b();
}

class Inner implements Interface {
  //@ ensures \result == false;
  public /*@ pure @*/ boolean b() {
    return false;
  }
}

class Outer implements Interface {
  /*@ spec_public @*/ private Interface inner;

  //@ public invariant inner != null;

  //@ requires inner != null;
  //@ ensures this.inner == inner;
  public Outer(Interface inner) {
    this.inner = inner;
  }

  //@ ensures \result == !inner.b();
  public /*@ pure @*/ boolean b() {
    return !inner.b();
  }
}

public class virtual_function_unwinding {
 
  public static boolean fun(String[] args) {
    return new Outer(new Inner()).b();
  }
}
