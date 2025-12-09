class classB {
  //@ requires true;
  //@ ensures \result == 123;
  public static int some_method() {
    return 123;
  }
}

class Main {
  //@ requires true;
  //@ ensures \result == true;
  //@ also
  //@ requires true;
  //@ ensures \result;
  public static boolean f() {
    int result = classB.some_method();
    return result == 123;
  }
}
