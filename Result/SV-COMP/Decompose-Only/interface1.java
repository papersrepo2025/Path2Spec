class cl {}

interface A {
  //@ requires false;
  //@ ensures true;
  public void f();
}

class B implements A {
  public void f() {
    assert false; // should fail
  }
}

class C implements A {
  public void f() {}
}

class Main {
  public static void main(String[] args) {
    A b = new B();
    A c = new C();
    //@ assume false;
    b.f(); // really calls B.f
  }
}