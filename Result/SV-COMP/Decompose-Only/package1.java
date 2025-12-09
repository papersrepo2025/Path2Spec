class classB {
  //@ requires true;
  //@ ensures \result == 123;
  //@ assignable \nothing;
  public static /*@ pure @*/ int some_method() {
    return 123;
  }
}

class Main {
  //@ requires true;
  //@ ensures \result;
  //@ assignable \nothing;
  public static /*@ pure @*/ boolean f() {
    //@ assert classB.some_method() == 123;
    int result = classB.some_method();
    return result == 123;
  }
}