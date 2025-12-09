class Main {
  //@ requires true;
  //@ ensures \result == true;
  public static boolean f() {
    //@ assert true;
    A[] as = {new A(), new B()};
    //@ assert as != null;
    //@ assert as.length == 2;
    //@ assert as[0] instanceof A;
    //@ assert as[1] instanceof A;
    if(!(as[0] instanceof A)) return false;
    if (!(as[1] instanceof A))
      return false;
    return true;
  }
}
;

class A {}

class B extends A {}