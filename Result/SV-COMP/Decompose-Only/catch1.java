class some_exception1 extends Throwable {}
;

class some_exception2 extends some_exception1 {}
;

class catch1 {
  //@ requires true;
  //@ ensures \result == true;
  public static boolean fun() {
    try {
      throw new some_exception2();
    } catch (some_exception1 e) {
      if(!(e instanceof some_exception2)) return false;
      if (e instanceof some_exception2) return true;
    }
    return false;
  }
}