class Main {
  //@ requires true;
  //@ ensures \result == true;
  public static boolean f() {
    A[] as = {new A(), new B()};
    if(!(as[0] instanceof A)) return false;
    if (!(as[1] instanceof A))
      return false;
    return true;
  }
}

class A {}

class B extends A {}