class classB {
  //@ ensures \result == 123;
  //@ assignable \nothing;
  public static int some_method() {
    return 123;
  }
}

class Main {
  //@ ensures \result;
  //@ assignable \nothing;
  public static boolean f() {
    int result = classB.some_method();
    return result == 123;
  }
}
;