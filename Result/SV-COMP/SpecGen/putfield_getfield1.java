class A {
  /*@ spec_public @*/ public int i;
}
;

class putfield_getfield1 {
  //@ ensures \result;
  public static boolean f() {
    A a = new A();
    a.i = 999;
    return 999 == a.i;
  }
}