class sub {}
;

class A {
 
  A() {}

 
  A(int param) {
    assert (my_field == 0);
    my_field = 2;
  }

  /*@ spec_public @*/ int my_field;
  /*@ spec_public nullable @*/ sub my_sub;

  static /*@ spec_public @*/ int something_static = 3;
}
;

class Main {
  //@ ensures true;
  public static void main(String[] args) {
    A some_a = new A();
    assert some_a.my_field == 0;
    assert some_a.my_sub == null;
    assert some_a.something_static == 3;
    A other_a = new A(1);
    assert other_a.my_field == 2;
    assert other_a.my_sub == null;
  }
}
;
