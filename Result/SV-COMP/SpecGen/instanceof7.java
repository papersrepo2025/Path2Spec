class Main {
  //@ ensures \result;
  public static boolean f() {
    A[] as = {new A(), new B()};
    if (!(!(as[0] instanceof B)))
      return false;
    if (!(as[1] instanceof B))
      return false;
    return true;
  }
}
;

class A {}

class B extends A {}