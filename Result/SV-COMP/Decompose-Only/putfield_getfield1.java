class A {
  public int i;
}
;

class putfield_getfield1 {
  //@ assignable \everything;
  //@ ensures \result;
  public static boolean f() {
    //@ assert true;
    A a = new A();
    //@ assert a != null;
    a.i = 999;
    return 999 == a.i;
  }
}