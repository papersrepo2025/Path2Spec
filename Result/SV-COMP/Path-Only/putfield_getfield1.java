class A {
  public int i;
}
;

class putfield_getfield1 {
  //@ requires true;
//@ ensures \result == true;
  public static boolean f() {
    A a = new A();
    a.i = 999;
    return 999 == a.i;
  }
}