class cl {}

interface A {
  public void f();
}

class B implements A {
  //@ requires true;
  //@ ensures true;
  public void f() {
    //@ assume false;
    assert false; // should fail
  }
}

class C implements A {
  public void f() {}
}

class Main {
  //@ requires true;
  //@ ensures true;
  public static void main(String[] args) {
    A b = new B();
    A c = new C();
    b.f(); // really calls B.f
  }
}
