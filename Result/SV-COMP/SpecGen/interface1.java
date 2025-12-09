class cl {}

interface A {
  //@ ensures true;
  public void f();
}

class B implements A {
  //@ ensures true;
  public void f() {
    //@ assume false;
    assert false; // should fail
  }
}

class C implements A {
  //@ ensures true;
  public void f() {}
}

class Main {
  //@ ensures true;
  public static void main(String[] args) {
    A b = new B();
    A c = new C();
    b.f(); // really calls B.f
  }
}